package ua.epam.training.kukuruza.controller.command;

import ua.epam.training.kukuruza.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class CommandFactory {
    private static final Map<String, ICommand> actions = new HashMap<>();
    private static final ICommand noCommand = new NoCommand();

    static {
        actions.put("changeSpecialty", new ChangeSpecialty());
        actions.put("checkStatus", new CheckStatus());
        actions.put("localization", new Localization());
        actions.put("exam", new ExamHandler());
        actions.put("examCancelRegistration", new ExamCancelRegistration());
        actions.put("examRegistration", new ExamRegistration());
        actions.put("results", new Results());
        actions.put("signIn", new SignIn());
        actions.put("signOut", new SignOut());
        actions.put("specialtySelection", new SpecialtySelection());
        actions.put("submitRequest", new SubmitRequest());
        actions.put("summaryRating", new SummaryRating());
        actions.put("registration", new Registration());
        actions.put("universitySelection", new UniversitySelection());
    }

    public static ICommand getAction(String commandName) {
        ICommand command = actions.get(commandName);
        return Objects.nonNull(command) ? command : noCommand;
    }
}
