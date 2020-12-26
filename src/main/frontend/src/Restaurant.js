import React,{useState,useEffect} from 'react';
import axios from "axios";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons'
import Modal from './Modal';
import './Restaurant.css';

const Restaurant = ({name,address,number,type,price,id,index}) =>{

    const setVisit=e=>{
        var today = new Date(),
        datenow = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
        axios.post("http://localhost:8080/api/v1/visit",{
            person_id: 'e5f3d266-70dd-4b47-85f6-6c8cddce6488',
            restaurant_id: e.target.value,
            date: datenow
        });
        window.location.reload(false);
      };

    const [modal,setModal] = useState(false);

    const [typeid,setTypeid] = useState('');

    const showModel=()=>{
        setModal(true);
    }
    
    const disableModel=()=>{
        setModal(false);
    }
    
    const fetchRestaurantType=()=>{
        axios.get(`http://localhost:8080/api/v1/type/${type}`).then(res=>{
            setTypeid(res.data.type);   
        })
    }

    useEffect(()=>{
        fetchRestaurantType();
    },[modal])
    return(
        <div>
            <div className="content-restaurant">
                <h1><a href="#" onClick={showModel} value={id}>{name}</a></h1>
                <button onClick={setVisit} value={id}><FontAwesomeIcon icon={faCheckCircle}/></button>
            </div>
            <Modal show={modal} handleClose={disableModel} name={name} address={address} price={price} number={number} type={typeid}/>
        </div>
    );
}
export default Restaurant;