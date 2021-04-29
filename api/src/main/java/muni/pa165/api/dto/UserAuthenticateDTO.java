package muni.pa165.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserAuthenticateDTO
{
    @Email
    private Long email;

    @Size(min = 6,max = 32)
    private String password;

    public Long getEmail()
    {
        return email;
    }

    public void setEmail(Long email)
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
