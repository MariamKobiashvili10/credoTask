package data.enums;

public enum LanguageData {
    GEORGIAN(
            "ქართული",
            "სავალდებულო ველი",
            "მონაცემები არასწორია",
            "პირი არ არის სრულწლოვანი",
            "დასაშვებია მხოლოდ 11 სიმბოლო",
            "დღე",
            "თვე",
            "წელი",
            "სისტემაში შესვლა",
            "რეგისტრაცია"
    ),

    ENGLISH(
            "English",
            "Required field",
            "Please make sure the entered details are correct",
            "This person is not of legal age, please check its information and try again",
            "Only 11 characters are allowed",
            "Day",
            "Month",
            "Year",
            "Log in",
            "Registration"

    );

    private final String nameOfLanguage;
    private final String requiredFieldsError;
    private final String invalidCredentialsError;
    private final String underagePersonError;
    private final String personalNumberFieldError;
    private final String day;
    private final String month;
    private final String year;
    private final String logInHeaderText;
    private final String registrationHeaderText;

    LanguageData(
            String nameOfLanguage,
            String requiredFieldsError,
            String invalidCredentialsError,
            String underagePersonError,
            String personalNumberFieldError,
            String day,
            String month,
            String year,
            String logInHeaderText,
            String registrationHeaderText
    ) {
        this.nameOfLanguage = nameOfLanguage;
        this.requiredFieldsError = requiredFieldsError;
        this.invalidCredentialsError = invalidCredentialsError;
        this.underagePersonError = underagePersonError;
        this.personalNumberFieldError = personalNumberFieldError;
        this.day = day;
        this.month = month;
        this.year = year;
        this.logInHeaderText = logInHeaderText;
        this.registrationHeaderText = registrationHeaderText;
    }

    public String getNameOfLanguage() {
        return nameOfLanguage;
    }

    public String getRequiredFieldsError() {
        return requiredFieldsError;
    }

    public String getInvalidCredentialsError() {
        return invalidCredentialsError;
    }

    public String getUnderagePersonError() {
        return underagePersonError;
    }

    public String getWrongLenghtPersonalNumberFieldError() {
        return personalNumberFieldError;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getLogInHeaderText() {
        return logInHeaderText;
    }

    public String getRegistrationHeaderText() {
        return registrationHeaderText;
    }


}