import * as React from "react";
import { Form } from "react-bootstrap";
import { UseFormRegisterReturn } from "react-hook-form";

export interface FormInputProps {
    name: string;
    register: UseFormRegisterReturn;
    title?: string;
    value?:string|number;
    type?: string;
    error?: string;
}

const FormInput: React.FC<FormInputProps> = ({ name, register, type = "text",value, title, error }) => {
    return (
        <Form.Group controlId={name}>
            <Form.Label>{title || name}</Form.Label>
            <Form.Control {...register} type={type} defaultValue={value || ""} name={name} placeholder={title || name} />
            <Form.Text className="text-danger">{error}</Form.Text>
        </Form.Group>
    );
};

export default FormInput;
