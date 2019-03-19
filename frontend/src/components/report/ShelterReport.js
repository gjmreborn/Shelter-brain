import React from "react";
import {Row, Col, CardColumns} from "reactstrap";
import Animal from "./Animal";

const ShelterReport = props => {
    const reportObj = props.report;

    return (
        <div>
            <Row>
                <Col>
                    <h2 className="text-center">Zwierzęta na stanie</h2>
                    <br />
                    {reportObj.animals.length !== 0 ?
                        <CardColumns>
                            {reportObj.animals.map(animal => <Animal key={animal.id} animal={animal} privileged={props.privileged} onDelete={props.onDelete} />)}
                        </CardColumns> :
                        <p className="text-center">Brak zwierząt</p>}
                </Col>
            </Row>
            <Row>
                <Col xs="6">
                    <u>{reportObj.occupiedPlaces} / {reportObj.maxAnimals} zajętych miejsc</u>
                </Col>
                <Col xs="6">
                    <b className={reportObj.shelterStatus === "FULL" ? "text-danger" : ""}>{reportObj.shelterStatus === "FULL" ?
                        "Schronisko pelne" : "Sa jeszcze wolne miejsca"}</b>
                </Col>
            </Row>
        </div>
    );  
};

export default ShelterReport;
