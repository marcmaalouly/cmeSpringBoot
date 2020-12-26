import React,{useState,useEffect} from 'react';
import axios from "axios";
import './App.css';
import Restaurant from './Restaurant';

function App() {

  const [search,setSearch] = useState("");

  const [restaurants,setRestaurants]=useState([]);

  const[url,setUrl]=useState('checkvisit/e5f3d266-70dd-4b47-85f6-6c8cddce6488');

  const fetchRestaurants= ()=>{
      axios.get(`http://localhost:8080/api/v1/restaurant/${url}`).then(res=>{
        setRestaurants(res.data);
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
    if(search=='' && selectedType!=''){
      setUrl(`type/${selectedType}`);
    }else{
      setUrl(`search/name=${search}`);
    }
    
  }
  
  const [types,setType]= useState([]);

  const [selectedType,setSelectedType]= useState('');

  const fetchType=()=>{
    axios.get("http://localhost:8080/api/v1/type").then(res=>{
      setType(res.data);
    })
  }

  useEffect(()=>{
    fetchType();
  },[])
  
  const getType=e=>{
    setSelectedType(e.target.value);
  }

 
  return (
    <div className="App">
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
        {restaurants.map((restaurants,index)=>(
          <Restaurant key={index} name={restaurants.name} address={restaurants.address} price={restaurants.avgcost} number={restaurants.phonenumber} type={restaurants.typeId} id={restaurants.id}/>
        ))}
      </div>
      
    </div>
  );
}

export default App;
