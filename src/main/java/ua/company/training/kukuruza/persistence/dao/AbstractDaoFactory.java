package ua.company.training.kukuruza.persistence.dao;

public abstract class AbstractDaoFactory {
    public abstract IDaoEducationOption getDaoEducationOption();
    public abstract IDaoExam getDaoExam();
    public abstract IDaoGrade getDaoGrade();
    public abstract IDaoRequest getDaoRequest();
    public abstract IDaoSpecialty getDaoSpecialty();
    public abstract IDaoSpecialtySubject getDaoSpecialtySubject();
    public abstract IDaoSubject getDaoSubject();
    public abstract IDaoUniversity getDaoUniversity();
    public abstract IDaoUser getDaoUser();
    public abstract IDaoUserExam getDaoUserExam();
    public abstract IDaoUserStatus getDaoUserStatus();
    public abstract IDaoUserType getDaoUserType();
}
