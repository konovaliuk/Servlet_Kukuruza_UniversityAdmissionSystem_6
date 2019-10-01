package ua.company.training.kukuruza.command;

import ua.company.training.kukuruza.command.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static ua.company.training.kukuruza.util.CommandNames.*;

public class CommandFactory {
    private static final Map<String, ICommand> actions = new HashMap<>();
    private static final ICommand noCommand = new NoCommand();

    static {
        actions.put(CHANGE_SPECIALTY_COMMAND, new ChangeSpecialty());
        actions.put(CHECK_STATUS_COMMAND, new CheckStatus());
        actions.put(EXAM_CANCEL_REGISTRATION_COMMAND, new ExamCancelRegistration());
        actions.put(EXAM_COMMAND, new ExamHandler());
        actions.put(EXAM_REGISTRATION_COMMAND, new ExamRegistration());
        actions.put(FIND_USER_SENDING_NOTIFICATION_COMMAND, new FindUserSendingNotification());
        actions.put(FIND_USER_SETTING_GRADE_COMMAND, new FindUserSettingGrade());
        actions.put(LOCALIZATION_COMMAND, new Localization());
        actions.put(REGISTRATION_COMMAND, new Registration());
        actions.put(RESULTS_COMMAND, new Results());
        actions.put(SET_GRADE_COMMAND, new SetGrade());
        actions.put(SET_USER_STATUS_COMMAND, new SetUserStatus());
        actions.put(SIGN_IN_COMMAND, new SignIn());
        actions.put(SIGN_OUT_COMMAND, new SignOut());
        actions.put(SPECIALTY_SELECTION_COMMAND, new SpecialtySelection());
        actions.put(SUBMIT_REQUEST_COMMAND, new SubmitRequest());
        actions.put(SUMMARY_RATING_COMMAND, new SummaryRating());
        actions.put(UNIVERSITY_SELECTION_COMMAND, new UniversitySelection());
    }

    public static ICommand getCommand(String commandName) {
        ICommand command = actions.get(commandName);
        return Objects.nonNull(command) ? command : noCommand;
    }
}
