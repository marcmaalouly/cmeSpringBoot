import React,{useState,useEffect} from 'react';
import axios from "axios";
import './App.css';
import Restaurant from './Restaurant';
import Pagination from './Pagination';

function Home(){
    
  const [loading,setLoading] = useState(false);
  const [search,setSearch] = useState("");
  const [currentPage,setCurrentPage] = useState(1);
  const [postsPerPage,setPostsPerPage] = useState(3);
  const [restaurants,setRestaurants]=useState([]);

  const[url,setUrl]=useState('checkvisit/e5f3d266-70dd-4b47-85f6-6c8cddce6488');

  const fetchRestaurants= async ()=>{
        setLoading(true);
        await axios.get(`http://localhost:8080/api/v1/restaurant/${url}`).then(res=>{
        setRestaurants(res.data);
        setTimeout(()=>{
            setLoading(false);
        },2000)
        
      });
  }

  useEffect(()=>{
    fetchRestaurants();
  },[url]);


  const updateSearch= e =>{
    setSearch(e.target.value);
  }

  const getSearch = e =>{
    e.preventDefault();
    console.log(selectedType);
    if(search==='' && selectedType!==''){
      setUrl(`type/${selectedType}`);
    }else{
      setUrl(`search/name=${search}`);
    }
    
  }
  
  const [types,setType]= useState([]);

  const [selectedType,setSelectedType]= useState('');

  const fetchType=async ()=>{
    setLoading(true);
    await axios.get("http://localhost:8080/api/v1/type").then(res=>{
      setType(res.data);
      setTimeout(()=>{
        setLoading(false);
    },2000)
    })
    
  }

  useEffect(()=>{
    fetchType();
  },[])
  
  const getType=e=>{
    setSelectedType(e.target.value);
  }

  const indexOfLastPost = currentPage * postsPerPage;
  const indexOfFirstPost= indexOfLastPost - postsPerPage ;
  const currentRest = restaurants.slice(indexOfFirstPost , indexOfLastPost);


  const paginate= pageNumbers => setCurrentPage(pageNumbers);
  if(loading){
    return(
        <div className="loader"></div>
    );
  }else{

    return (
        <div>
        <div className="nav">
            <form onSubmit={getSearch}>
            <input type="text" className="search-bar"  value={search} onChange={updateSearch} /> 
            <select onChange={getType}>
                <option value=''>Type</option>
                {types.map((types,index)=>{
                    return(
                        <option value={types.id} key={index}>{types.type}</option>
                    )      
                })}
            </select>    
            <input type="submit" className="search-button" />
            </form>
        </div>


        <div className="listRestaurants">
            {currentRest.map((restaurants,index)=>(
            <Restaurant key={index} name={restaurants.name} address={restaurants.address} price={restaurants.avgcost} number={restaurants.phonenumber} type={restaurants.typeId} id={restaurants.id}/>
            ))}
        </div>
        <Pagination restaurantsPerPage={postsPerPage} totalRestaurants={restaurants.length} paginate={paginate}/>
        </div>
    );
    }
}
export default Home;