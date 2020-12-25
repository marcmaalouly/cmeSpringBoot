import React,{useState,useEffect} from 'react';
import axios from "axios";

function Restaurant(){

    const [restaurants,setRestaurants]=useState([]);
    
    const fetchRestaurants= ()=>{
        axios.get("http://localhost:8080/api/v1/restaurant/checkvisit/e5f3d266-70dd-4b47-85f6-6c8cddce6488").then(res=>{
            console.log(res);
            setRestaurants(res.data);
        });
    }

    useEffect(()=>{
        fetchRestaurants();
    },[]);

    const setVisit=()=>{
        var today = new Date(),
        datenow = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
        axios.post("http://localhost:8080/api/v1/visit",{
            person_id: 'e5f3d266-70dd-4b47-85f6-6c8cddce6488',
            restaurant_id: 'f178200a-6e13-4009-8724-fe5370c274be',
            date: datenow
        });
        window.location.reload(false);
    };

    return restaurants.map((restaurants,index)=>{
        return(
            <div className="listRestaurants" key={index}>
                <h1>{restaurants.name}</h1>
                <button onClick={setVisit}>Visit</button>
            </div>
        )
    });
}
export default Restaurant;