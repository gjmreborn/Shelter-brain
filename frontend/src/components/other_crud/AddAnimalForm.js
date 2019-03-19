import React from "react";
import {withRouter} from "react-router-dom";
import DismissableAlert from "../../utils/DismissableAlert";
import axios from "axios";
import Config from "../../Config";

class AddAnimalForm extends React.Component {
    state = {
        name: "",
        age: -1,
        gender: "MALE",         // default checked
        base64PngImage: "",

        errorMessage: null
    }

    onAddAnimal = e => {
        e.preventDefault();

        const jwt = sessionStorage.getItem("jwt");
        e.target.reset();
        
        axios.post(`${Config.AJAX_BASE_URL}/animals`, {
            name: this.state.name,
            age: Number(this.state.age),
            gender: this.state.gender,
            base64Image: this.state.base64PngImage
        }, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(() => this.props.onAddAnimal())
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

    genericOnChangeEventHandler = e => this.setState({[e.target.name]: e.target.value});
    genericOnChangeCheckboxesEventHandler = e => {
        if(e.target.checked) {
            this.setState({[e.target.name]: e.target.value});
        }
    }

    render() {
        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }

        const randomValueForId = Math.round(Math.random() * 100);

        return (
            <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <h3>Dodaj nowe zwierzątko</h3>
                <form onSubmit={this.onAddAnimal}>
                    <div className="form-group">
                        <label htmlFor={`name_${randomValueForId}`}>Nazwa</label>
                        <input type="text" name="name" className="form-control" id={`name_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                            required={true} placeholder="Wpisz nazwę/imię zwierzaka..." />
                            </div>
                    <div className="form-group">
                        <label htmlFor={`age_${randomValueForId}`}>Wiek</label>
                        <input type="number" min="1" max="100" name="age" className="form-control" id={`age_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                            required={true} placeholder="Wpisz wiek zwierzaka..." />
                    </div>
                    <fieldset className="form-group">
                            <legend className="col-form-label pt-0">Płeć</legend>
                            <div className="form-check">
                                <input className="form-check-input" type="radio" name="gender"
                                    onChange={this.genericOnChangeCheckboxesEventHandler} id={`gender_male_${randomValueForId}`} value="MALE" checked={true} />
                                <label className="form-check-label" htmlFor={`gender_male_${randomValueForId}`}>Męska</label>
                            </div>
                            <div className="form-check">
                                <input className="form-check-input" type="radio" name="gender"
                                    onChange={this.genericOnChangeCheckboxesEventHandler} id={`gender_female_${randomValueForId}`} value="FEMALE" />
                                <label className="form-check-label" htmlFor={`gender_female_${randomValueForId}`}>Żeńska</label>
                            </div>
                    </fieldset>
                    <div className="form-group">
                        <label htmlFor={`animal_image_${randomValueForId}`}>Wybierz zdjęcie zwierzaka</label>
                        <input required={true} type="file" className="form-control-file" id={`animal_image_${randomValueForId}`}
                            accept="image/png" onChange={e => {
                                const file = e.target.files[0];

                                if(file.type !== "image/png") {
                                    this.setState({errorMessage: `Zły format zdjęcia profilowego (${file.type} zamiast png)`});
                                    e.target.value = "";
                                    return;
                                }

                                const fileReader = new FileReader();
                                fileReader.readAsBinaryString(file);
                                fileReader.onload = () => this.setState({base64PngImage: btoa(fileReader.result)});
                            }}/>
                    </div>
                    <button type="submit" className="btn btn-primary">Dodaj</button>

                    {this.state.errorMessage !== null &&
                        <div>
                            <br />
                            <br />
                            <DismissableAlert key={Math.random()} color="danger" timeout={3} toggle={() => this.setState({errorMessage: null})}>
                                {this.state.errorMessage}
                            </DismissableAlert>
                        </div>
                    }
                </form>
            </div>
        );
    }
}

export default withRouter(AddAnimalForm);
