import React,{useState,useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import axios from "axios";

function UserPage(){

    const [rows,setRows] = useState([]);

    const fetchRows= () =>{
        axios.get("http://localhost:8080/api/v1/visit/e5f3d266-70dd-4b47-85f6-6c8cddce6488").then(res=>{
            console.log(res);
            setRows(res.data);
        });
    }


    useEffect(()=>{
        fetchRows();
    },[]);

    const useStyles = makeStyles({
        table: {
          minWidth: 650,
        },
      });
    
    const classes = useStyles();

    return(
        <div>
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead>
                    <TableRow>
                        <TableCell>Restaurant</TableCell>
                        <TableCell align="right">Date</TableCell>
                    </TableRow>
                    </TableHead>
                    <TableBody>
                    {rows.map((row) => (
                        <TableRow key={row.restaurant_id}>
                        <TableCell component="th" scope="row">
                            {row.restName}
                        </TableCell>
                        <TableCell align="right">{row.date}</TableCell>
                        </TableRow>
                    ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}

export default UserPage;