/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;

/**
 *
 * @author enio1
 */
@Named
@RequestScoped
public class LoginController {
    
      @NotEmpty
      private String usuario;
      
      @NotEmpty
      private String senha;
      
      @Inject
      FacesContext facesContext;
      
      @Inject
      SecurityContext securityContext;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
      
     public void execute() throws IOException {
        switch (processAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
                break;
            case SUCCESS:
                getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/administrador/index.xhtml");
                break;
        }
    }

    private AuthenticationStatus processAuthentication() {
        ExternalContext ec = getExternalContext();
        return securityContext.authenticate(
                (HttpServletRequest) ec.getRequest(),
                (HttpServletResponse) ec.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(usuario, senha)));
    }

    private ExternalContext getExternalContext() {
        return facesContext.getExternalContext();
    }  
      
    
}
