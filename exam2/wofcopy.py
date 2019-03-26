import random
#picks phrase from phrasebank
def choose_phrase(num):
    PhraseBank = open("phrasebank.txt").read().splitlines()
    return PhraseBank[num]
#sets category repending on phrase location in phrasebank
def set_category(num):
    if num >= 0 and num < 20:
        return "Before and After"
    elif num >= 20 and num < 40:
        return "Song Lyrics"
    elif num >= 40 and num < 60:
        return "Around the House"
    elif num >= 60 and num <80:
        return "Food and Drink"
    elif num >= 80 and num <=99:
        return "Same Name"
#welcomes the player, prints category and phrase in blanks, and prints current winnings ($0)
def introduceWheelofFortune(phrase, category):
    guess_progress = ["_" for letter in phrase]
    x = -1
    for underscore in guess_progress:
        x = x + 1
        if phrase[x] == " ":
            guess_progress[x] = " "
    guess_progress_string = " ".join(guess_progress)
    print("Welcome to the Wheel of Fortune!")
    print("")
    print("The phrase is: ")
    print(guess_progress_string)
    print("The category is: " + category)
    print("")
    print("Your current winnings are: $0")
    print("")
    return guess_progress
#updates the player on the letters they've guessed and how many letters are still blank and fills in correctly guessed letters for blanks in the phrase
def status(phrase, guess_progress, vowels_guessed, consonants_guessed, current_winnings):
    x = -1
    for letter in phrase:
        x = x + 1
        for consonant in consonants_guessed:
                if letter == consonant:
                    guess_progress[x] = consonant
    x = -1
    for letter in phrase:
        x = x + 1
        for vowel in vowels_guessed:
            if letter == vowel:
                guess_progress[x] = vowel
    guess_progress_string = " ".join(guess_progress)
    consonants_guessed_string = ",".join(consonants_guessed)
    vowels_guessed_string = ",".join(vowels_guessed)
    print("")
    print("The phrase is: ")
    print(guess_progress_string)
    print("")
    print("Vowels Guessed: " + vowels_guessed_string)
    print("Consonants Guessed: " + consonants_guessed_string)
    print("Your current winnings are: $" + str(current_winnings))
    return guess_progress
#Spins the wheel and lands on a number before letting the user guess a consonant and updating the game status
def spinTheWheel(phrase, current_winnings, consonants_guessed):
    wheel_values = [50,100,100,100,100,100,100,200,200,200,200,250,250,250,500,500,750,750,1000,2000,5000,10000,"Bankrupt","Bankrupt"]
    consonants = ['B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z']
    num_of_consonant_appearances = 0
    x = 0
    isConsonant = False
    isConsonantinPhrase = False
    correctGuess = False
    wheel_spin_value = wheel_values[random.randint(0,23)]
    if wheel_spin_value == "Bankrupt":
        print("Sorry, you've spun a Bankrupt! Your winnings will go down to $0.")
        current_winnings = 0
    else:
        print("You've spun $" + str(wheel_spin_value) + "!")
#Checks that you haven't already guessed all the consonants
        if len(consonants_guessed) == len(consonants):
            print("You have already guessed all possible consonants. Try buying a vowel or solving the puzzle.")
        else:
            consonant_guess = str(input("Guess a consonant: "))
#makes sure that input is a letter
            while consonant_guess.isalpha() == False:
                print("Try again. Input must be a letter")
                consonant_guess = str(input("Guess a consonant: "))
            consonant_guess = consonant_guess.upper()
            while consonants.count(consonant_guess) == 0 or consonants_guessed.count(consonant_guess) != 0:
                print("Try again. Input must be a consonant and you can't have already guessed it.")
                consonant_guess = str(input("Guess a consonant: "))
                consonant_guess = consonant_guess.upper()
            consonants_guessed += consonant_guess
#checks if consonant is in the phrase
            i = -1
            for letter in phrase:
                i = i + 1
                if phrase[i] == consonant_guess:
                    correctGuess = True
            if correctGuess == True:
                current_winnings = current_winnings + (wheel_spin_value * phrase.count(consonant_guess))
                print("Congratulations, " + consonant_guess + " appears in the phrase " + str(phrase.count(consonant_guess)) + " times!")
                print("You've won $" + str(wheel_spin_value * phrase.count(consonant_guess)) + ".")
            else:
                current_winnings = current_winnings - wheel_spin_value
                print("Sorry, " + consonant_guess + " is not in the phrase.")
                print("$" + str(wheel_spin_value) + " dollars will be deducted from your winnings.")
    return current_winnings
    return consonants_guessed

def buyAVowel(phrase, current_winnings, vowels_guessed):
    vowels = ['A','E', 'I', 'O', 'U']
    correctGuess = False
#Checks that you haven't already guessed all the vowels
    if len(vowels_guessed) == len(vowels):
        print("You have already guessed all possible vowels. Try spinning the wheel or solving the puzzle.")
    else:
        if current_winnings >= 250:
            print("Vowels cost $250.")
            vowel_guess = input("Which vowel would you like to buy? ")
            while vowel_guess.isalpha() == False:
                print("Try again. Input must be a letter")
                vowel_guess = str(input("Which vowel would you like to buy? "))
            vowel_guess = vowel_guess.upper()
            while vowels.count(vowel_guess) == 0 or vowels_guessed.count(vowel_guess) != 0:
                print("Try again. Input must be a vowel and you can't have already guessed it.")
                vowel_guess = input("Which vowel would you like to buy? ")
                vowel_guess = vowel_guess.upper()
            vowels_guessed += vowel_guess
            current_winnings = current_winnings - 250
            i = -1
            for letter in phrase:
                i = i + 1
                if phrase[i] == vowel_guess:
                    correctGuess = True
            if correctGuess == True:
                print("Congratulations, " + vowel_guess + " appears in the phrase " + str(phrase.count(vowel_guess)) + " times!")
            else:
                print("Sorry, that consonant is not in the phrase.")
        else:
            print("You do not have enough money to buy a vowel.")
    return current_winnings
    return vowels_guessed

def solveThePuzzle(phrase):
    gameWon = False
    guess = input("What is your guess (Enter your guess with single spaces)? ")
    guess_copy = guess.replace(" ", "")
    if guess_copy.isalpha() == True:
        guess = guess.upper()
    if guess == phrase:
        gameWon = True
    else:
        print("Sorry, that guess is not correct. Your winnings will return to $0.")
        gameWon = False
    return gameWon

def main():
    consonants = ['b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z']
    vowels = ['a','e','i','o','u']
    consonants_guessed = []
    vowels_guessed = []
    current_winnings = 0
    num = random.randint(0,99)
    phrase = str(choose_phrase(num))
    phrase = phrase.upper()
    category = set_category(num)
    guess_progress = introduceWheelofFortune(phrase, category)
    while "".join(guess_progress) != phrase:
        choice = input("Would you like to Spin the Wheel (type 'spin'), Buy a Vowel (type 'vowel'), or Solve the Puzzle (type 'solve')? ")
        while choice.isalpha() == False:
            print("Try again.")
            choice = input("Would you like to Spin the Wheel (type 'spin'), Buy a Vowel (type 'vowel'), or Solve the Puzzle (type 'solve')? ")
            choiceIsAlpha = choice.isalpha()
        choice.lower()
        print(phrase)
        while choice != "spin" and choice != "vowel" and choice != "solve":
            print("Try again.")
            choice = input("Would you like to Spin the Wheel (type 'spin'), Buy a Vowel (type 'vowel'), or Solve the Puzzle (type 'solve')? ")
        if choice == "spin":
            current_winnings = spinTheWheel(phrase, current_winnings, consonants_guessed)
            guess_progress = status(phrase, guess_progress, vowels_guessed, consonants_guessed, current_winnings)
        elif choice == "vowel":
            current_winnings = buyAVowel(phrase, current_winnings, vowels_guessed)
            guess_progress = status(phrase, guess_progress, vowels_guessed, consonants_guessed, current_winnings)
        elif choice == "solve":
            gameWon = solveThePuzzle(phrase)
            if gameWon == True:
                print("That's correct - you solved the puzzle!")
                guess_progress = phrase
            else:
                current_winnings = 0
                print("The category is: " + category)
                status(phrase, guess_progress, vowels_guessed, consonants_guessed, current_winnings)
    if len(consonants_guessed) == len(consonants) and len(vowels_guessed) == len(vowels):
        print("Sorry, you have guessed all possible letters and have lost the game. Your winnings are $0.")
        print('The phrase was: "' + phrase_original + '."')
        print("Thank you for playing the wheel of Fortune!")
    else:
        print("Congratulations, you won the game! Your winnings are " + str(current_winnings) + ".")
        print("Thank you for playing the Wheel of Fortune!")

if __name__ == "__main__":
    main()
