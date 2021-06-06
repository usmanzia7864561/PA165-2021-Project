const emailValidation = (value: string): boolean | string => {
    const email = new RegExp(/^[^\s@]+@[^\s@]+$/);
    return email.test(value) || "Email is not valid";
};

export default emailValidation;
