import * as React from "react";
import { Button, Col, Row, Table } from "react-bootstrap";
import { Link } from "react-router-dom";

export interface TableWrapperProps {
    title: string;
    addText?: string;
    addLink?: string;
}

const TableWrapper: React.FC<TableWrapperProps> = ({ title, addText, addLink, children }) => {
    return (
        <div>
            <Row className="mb-3">
                <Col>
                    <h5 className="mb-0">{title}</h5>
                </Col>
                <Col className="ml-auto text-right">
                    {addLink && addText ? (
                        <Button as={Link} to={addLink} size="sm" variant="primary">
                            {addText}
                        </Button>
                    ) : null}
                </Col>
            </Row>
            <Table className="text-center">{children}</Table>
        </div>
    );
};

export default TableWrapper;
