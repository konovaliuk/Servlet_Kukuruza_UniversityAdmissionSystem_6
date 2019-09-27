package ua.company.training.kukuruza.dao.daoMySql;

import ua.company.training.kukuruza.dbConnection.ConnectionFactoryImpl;
import ua.company.training.kukuruza.dbConnection.IConnectionFactory;
import ua.company.training.kukuruza.dao.*;

public class MySqlDaoFactory extends AbstractDaoFactory {
    private static final MySqlDaoFactory INSTANCE = new MySqlDaoFactory(ConnectionFactoryImpl.getInstance());
    private IDaoEducationOption daoEducationOption;
    private IDaoExam daoExam;
    private IDaoGrade daoGrade;
    private IDaoRequest daoRequest;
    private IDaoSpecialty daoSpecialty;
    private IDaoSpecialtySubject daoSpecialtySubject;
    private IDaoSubject daoSubject;
    private IDaoUniversity daoUniversity;
    private IDaoUser daoUser;
    private IDaoUserExam daoUserExam;
    private IDaoUserStatus daoUserStatus;
    private IDaoUserType daoUserType;

    private MySqlDaoFactory(IConnectionFactory cf) {
        daoEducationOption = new MySqlDaoEducationOption(cf);
        daoExam = new MySqlDaoExam(cf);
        daoGrade = new MySqlDaoGrade(cf);
        daoRequest = new MySqlDaoRequest(cf);
        daoSpecialty = new MySqlDaoSpecialty(cf);
        daoSpecialtySubject = new MySqlDaoSpecialtySubject(cf);
        daoSubject = new MySqlDaoSubject(cf);
        daoUniversity = new MySqlDaoUniversity(cf);
        daoUser = new MySqlDaoUser(cf);
        daoUserExam = new MySqlDaoUserExam(cf);
        daoUserStatus = new MySqlDaoUserStatus(cf);
        daoUserType = new MySqlDaoUserType(cf);
    }

    public static MySqlDaoFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public IDaoEducationOption getDaoEducationOption() {
        return daoEducationOption;
    }

    @Override
    public IDaoExam getDaoExam() {
        return daoExam;
    }

    @Override
    public IDaoGrade getDaoGrade() {
        return daoGrade;
    }

    @Override
    public IDaoRequest getDaoRequest() {
        return daoRequest;
    }

    @Override
    public IDaoSpecialty getDaoSpecialty() {
        return daoSpecialty;
    }

    @Override
    public IDaoSpecialtySubject getDaoSpecialtySubject() {
        return daoSpecialtySubject;
    }

    @Override
    public IDaoSubject getDaoSubject() {
        return daoSubject;
    }

    @Override
    public IDaoUniversity getDaoUniversity() {
        return daoUniversity;
    }

    @Override
    public IDaoUser getDaoUser() {
        return daoUser;
    }

    @Override
    public IDaoUserExam getDaoUserExam() {
        return daoUserExam;
    }

    @Override
    public IDaoUserStatus getDaoUserStatus() {
        return daoUserStatus;
    }

    @Override
    public IDaoUserType getDaoUserType() {
        return daoUserType;
    }
}
