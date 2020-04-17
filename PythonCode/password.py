def passwordCheck(password):
    upper_case_requirement = 0
    digit_requirement = 0
    non_letter_or_digit_requirement = 0
    password_made = 0
    for i in password:
        if i.isalpha() == True and i == i.upper():
            upper_case_requirement =  1
        elif i.isdigit() == True:
            digit_requirement = digit_requirement + 1
        elif i.isalpha() == False and i.isdigit() == False:
            non_letter_or_digit_requirement = 1
    if len(password) >= 8 and upper_case_requirement >= 1 and digit_requirement >= 2 and non_letter_or_digit_requirement >= 1:
        password_made = 1
        return password_made
    else:
        return password_made


def main():
    password = input("Enter password: ")
    return passwordCheck(password)
    while password_made == 0:
        print(str(password) + " is not a valid password.")
        password = input("Enter password: ")
        passwordCheck(password)

if __name__ == "__main__":
    main()
