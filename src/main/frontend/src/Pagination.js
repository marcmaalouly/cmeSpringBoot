import React from 'react';
import './Pagination.css';

const Pagination = ({restaurantsPerPage , totalRestaurants ,paginate}) =>{

    const pageNumbers = [];

    for(let i=1 ;i <= Math.ceil(totalRestaurants / restaurantsPerPage); i++){
        pageNumbers.push(i);
    }

    return(
        <div>
            <div className="pagination">
                {pageNumbers.map(number=>(
                    <a onClick={()=>paginate(number)} href="#" key={number}>{number}</a>
                ))}
            </div>
        </div>
    );
}
export default Pagination;