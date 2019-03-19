import React from "react";
import ShelterReportPanel from "../components/report/ShelterReportPanel";
import {withRouter} from "react-router-dom";
import {Container, Row, Col, Navbar, NavbarBrand, Nav, NavItem} from "reactstrap";
import LogoutButton from "../components/auth/LogoutButton";
import axios from "axios";
import DismissableAlert from "../utils/DismissableAlert";
import AddAnimalForm from "../components/other_crud/AddAnimalForm"
import PersonalAccountProfile from "../components/PersonalAccountProfile";
import Config from "../Config";

class ProfilePage extends React.Component {
    state = {
        errorMessage: null,

        account: null
    };

    onDelete = animalToDelete => {
        const jwt = sessionStorage.getItem("jwt");
        
        axios.delete(`${Config.AJAX_BASE_URL}/animals/${animalToDelete.id}`, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(() => this.forceUpdate())
            .catch(err => {
                if(err.response !== undefined && err.response.status === 401) {
                    // JWT expired (probably server restart)
                    sessionStorage.removeItem("jwt");
                    this.props.history.push("/");
                } else {
                    this.setState({errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem"});
                }
            });
    };

    componentDidMount() {
        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }

        axios.get(`${Config.AJAX_BASE_URL}/account`, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(res => this.setState({account: res.data}))
            .catch(err => {
                if(err.response !== undefined && err.response.status === 401) {
                    // JWT expired (probably server restart)
                    sessionStorage.removeItem("jwt");
                    this.props.history.push("/");
                } else {
                    this.setState({
                        errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem",
                        account: null
                    });
                }
            });
    }

    render() {
        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }

        return (
            <div>
                <Navbar color="light" light expand="md">
                    <NavbarBrand>Centrala schroniska</NavbarBrand>
                    <Nav className="ml-auto" navbar>
                        <NavItem>
                            <LogoutButton />
                        </NavItem>
                    </Nav>
                </Navbar>

                <Container>
                    <Row>
                        <Col>
                            <h1 className="text-center">Panel pracownika schroniska</h1>
                            {this.state.account !== null && <PersonalAccountProfile account={this.state.account} />}
                            <br />
                            <ShelterReportPanel key={Math.random()} privileged={true} onDelete={this.onDelete} />
                            <br />
                            {this.state.errorMessage !== null &&
                                <DismissableAlert key={Math.random()} color="danger" timeout={3} toggle={() => this.setState({errorMessage: null})}>
                                    {this.state.errorMessage}
                                </DismissableAlert>}
                        </Col>
                    </Row>
                    <br />
                    <br />
                    <Row>
                        <Col>
                              <AddAnimalForm onAddAnimal={() => this.forceUpdate()} />
                        </Col>
                    </Row>
                    <br />
                </Container>
            </div>
        );
    }
}

export default withRouter(ProfilePage);
