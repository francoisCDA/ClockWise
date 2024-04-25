import {Container} from "react-bootstrap";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";


const ReportTable = ({reports}) =>{
    return(
        <Container fluid="md">
            <Table striped bordered hover responsive="md">
                <thead>
                <tr>
                    <th>date</th>
                    <th>titre</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {reports.map((report) => (
                    <tr key={report.id}>
                        <td>{report.title}</td>
                        <td>{}</td>
                    </tr>

                ))}
                <tr>
                    <td>
                        <Button variant="outline-info"> <span>Details</span></Button>
                        <Button variant="outline-warning"><span>Edit</span></Button>
                    </td>
                </tr>
                </tbody>
            </Table>
        </Container>
    )
}

export default ReportTable;