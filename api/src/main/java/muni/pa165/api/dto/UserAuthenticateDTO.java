package muni.pa165.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserAuthenticateDTO
{
    @Email
    private String email;

    @Size(min = 6,max = 32)
    private String password;

    public UserAuthenticateDTO(){

    }

    public UserAuthenticateDTO(String email,String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
