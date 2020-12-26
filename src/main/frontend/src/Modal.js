import React from 'react';
import './Modal.css';
const Modal = ({handleClose,show,name,address,price,number,type}) =>{
    const showHideClassName= show ? "modal display-block" : "modal display-none";
    return(
        <div className={showHideClassName}>
            <section className="modal-main">
                <input type="text" value={name}/>
                <br/>
                <input type="text" value={type}/>
                <br/>
                <input type="text" value={price}/>
                <br/>
                <input type="text" value={address}/>
                <br/>
                <input type="text" value={number}/>
                <br/>
                <button type="button" onClick={handleClose}>Close</button>
            </section>
        </div>
    )
}

export default Modal;