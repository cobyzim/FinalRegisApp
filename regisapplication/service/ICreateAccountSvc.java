package zimmerman.regis.regisapplication.service;

import java.util.List;

import zimmerman.regis.regisapplication.domain.Classes;

//CRUD interface for Create Account
public interface ICreateAccountSvc {

    public Classes create(Classes classes);
    public List<Classes> retrieveAll();
    public Classes update(Classes classes);
    public Classes delete(Classes classes);
}
