package ua.epam.training.kukuruza.model.dao;

public abstract class AbstractDaoFactory {
    public abstract IDaoApplication getDaoApplication();
    public abstract IDaoExam getDaoExam();
    public abstract IDaoGrade getDaoGrade();
    public abstract IDaoSpecialty getDaoSpecialty();
    public abstract IDaoSpecialtyUniversityLimit getDaoSpecialtyUniversityLimit();
    public abstract IDaoSubject getDaoSubject();
    public abstract IDaoUniversity getDaoUniversity();
    public abstract IDaoUser getDaoUser();
    public abstract IDaoUserType getDaoUserType();
}
