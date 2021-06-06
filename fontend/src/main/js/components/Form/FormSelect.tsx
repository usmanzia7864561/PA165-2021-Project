import * as React from "react";
import { Form } from "react-bootstrap";
import { UseFormRegisterReturn } from "react-hook-form";

export interface FormInputProps {
    name: string;
    register: UseFormRegisterReturn;
    title?: string;
    type?: string;
    value?:string;
    error?: string;
}

const FormSelect: React.FC<FormInputProps> = ({ name, register, type = "text",value, title, error, children }) => {
    return (
        <Form.Group controlId={name}>
            <Form.Label>{title || name}</Form.Label>
            <Form.Control as="select" {...register} type={type} name={name} defaultValue={value ||""}>
                {children}
            </Form.Control>
            <Form.Text className="text-danger">{error}</Form.Text>
        </Form.Group>
    );
};

export default FormSelect;
