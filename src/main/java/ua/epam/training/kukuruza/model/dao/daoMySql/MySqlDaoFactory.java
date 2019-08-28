package ua.epam.training.kukuruza.model.dao.daoMySql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.epam.training.kukuruza.model.connection.ConnectionFactoryImpl;
import ua.epam.training.kukuruza.model.connection.IConnectionFactory;
import ua.epam.training.kukuruza.model.dao.*;

import java.util.Objects;

public class MySqlDaoFactory extends AbstractDaoFactory {
    private static final Logger LOGGER = LogManager.getLogger(MySqlDaoFactory.class);
    private static MySqlDaoFactory instance;
    private MySqlDaoUserType daoUserType;
    private MySqlDaoUser daoUser;
    private MySqlDaoUniversity daoUniversity;
    private MySqlDaoSubject daoSubject;
    private MySqlDaoSpecialty daoSpecialty;
    private MySqlDaoRequest daoRequest;
    private MySqlDaoGrade daoGrade;
    private MySqlDaoExam daoExam;
    private MySqlDaoEducationOption daoEducationOption;

    private MySqlDaoFactory(IConnectionFactory cf) {
        daoUserType = new MySqlDaoUserType(cf);
        daoUser = new MySqlDaoUser(cf);
        daoUniversity = new MySqlDaoUniversity(cf);
        daoSubject = new MySqlDaoSubject(cf);
        daoSpecialty = new MySqlDaoSpecialty(cf);
        daoRequest = new MySqlDaoRequest(cf);
        daoGrade = new MySqlDaoGrade(cf);
        daoExam = new MySqlDaoExam(cf);
        daoEducationOption = new MySqlDaoEducationOption(cf);
    }

    public static MySqlDaoFactory getInstance() {
        if (Objects.isNull(instance)) {
            LOGGER.debug("Begin of creating instance");
            instance = createInstance();
            LOGGER.debug("Successful creating instance");
        }
        return instance;
    }

    private static synchronized MySqlDaoFactory createInstance() {
        if (Objects.nonNull(instance)) {
            LOGGER.debug("Another thread created instance");
            return instance;
        }
        return new MySqlDaoFactory(ConnectionFactoryImpl.getInstance());
    }

    @Override
    public IDaoRequest getDaoRequest() {
        return daoRequest;
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
    public IDaoSpecialty getDaoSpecialty() {
        return daoSpecialty;
    }

    @Override
    public IDaoEducationOption getDaoEducationOption() {
        return daoEducationOption;
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
    public IDaoUserType getDaoUserType() {
        return daoUserType;
    }
}
