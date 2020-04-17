# Evan Newlund newlu004

# I understand this is a graded, individual examination that may not be
# discussed with anyone. I also understand that obtaining solutions or
# partial solutions from outside sources, or discussing
# any aspect of the examination with anyone will result in failing the course.
# I further certify that this program represents my own work and that none of
# it was obtained from any source other than material presented as part of the
# course.

import turtle
import random

#initializes board by creating green squares and setting row and column markers
def createBoard():
    turtle.setworldcoordinates(0.00,0.00,7.00,7.00)
    t = turtle.Turtle()
    t.speed(0)
    t.pensize(2)
    t.pencolor("white")
    t.hideturtle()
    t.fillcolor("green")
#creates square board made up of 8 rows of 8 squares
    for row in range(8):
        for col in range(8):
            t.penup()
            t.goto(col,row)
            t.pendown()
            t.begin_fill()
            for i in range(4):
                t.forward(1)
                t.right(90)
            t.end_fill()
    t.penup()
    t.color("black")
#adds markers above first row and to the left of the first column
    x = 0
    for i in range(8):
        t.goto(i + 0.5,7)
        t.write(x)
        t.goto(-0.075,6.5-x)
        t.write(x)
        x += 1
#plots initial pieces in the middle of the board; two pieces for the player and two for their computer opponent
    t.goto(3.5,2.5)
    t.dot(70)
    t.goto(4.5,3.5)
    t.dot(70)
    t.color("white")
    t.goto(3.5,3.5)
    t.dot(70)
    t.goto(4.5,2.5)
    t.dot(70)
#creates a matrix representing the board with each value initially set to "unoccupied"
    board = []
    for i in range(8):
        board.append(["unoccupied"] * 8)
#updates the matrix by adding the initial 4 pieces
    board[3][3] = "white"
    board[3][4] = "black"
    board[4][3] = "black"
    board[4][4] = "white"
    return board

#determines if a point is within the grid or on the borders of the grid
def inGrid(row,col):
    if row == 0:
        return False
    elif col == 0:
        return False
    elif row == 7:
        return False
    elif col == 7:
        return False
    else:
        return True

#determines all adjacent opponent pieces for a given grid point and returns a list of them
def getNeighbors(board,row,col,color):
    opponent_Neighbors = []
#if the grid point isn't on one of the boarders or corners, its adjacent pieces are, simply, the 8 pieces directly surrounding it
    if inGrid(row,col):
        neighbors = [[row-1,col-1],[row-1,col],[row-1,col+1],[row,col-1],[row,col+1],[row+1,col-1],[row+1,col],[row+1,col+1]]
#finds adjacent pieces if grid point lies on one of the board edges or corners
    else:
        if row == 0:
            if col == 0:
                neighbors = [[0,1],[1,0],[1,1]]
            elif col == 7:
                neighbors = [[0,6],[1,7],[1,6]]
            else:
                neighbors = [[row,col-1],[row,col+1],[row+1,col-1],[row+1,col],[row+1,col+1]]
        elif row == 7:
            if col == 0:
                neighbors = [[6,0],[6,1],[7,1]]
            elif col == 7:
                neighbors = [[6,7],[6,6],[7,6]]
            else:
                neighbors = [[row,col-1],[row,col+1],[row-1,col-1],[row-1,col],[row-1,col+1]]
        elif col == 0 and row != 0 and row != 7:
            neighbors = [[row-1,col],[row-1,col+1],[row,col+1],[row+1,col],[row+1,col+1]]
        elif col == 7 and row != 0 and row != 7:
            neighbors = [[row-1,col],[row-1,col-1],[row,col-1],[row+1,col],[row+1,col-1]]
#removes all unoccupied cells and friendly pieces
    for cell in neighbors:
        if board[cell[0]][cell[1]] != "unoccupied" and board[cell[0]][cell[1]] != color:
                opponent_Neighbors.append(cell)
    return opponent_Neighbors

#scans every position on the board and compiles a list of possible moves for a given player
def getValidMoves(board,color):
    validMoves = []
    row_location = -1
    col_location = -1
    for row in board:
        row_location += 1
        col_location = -1
        for col in row:
            col_location += 1
            opponent_Neighbors = getNeighbors(board,row_location,col_location,color)
            if board[row_location][col_location] == "unoccupied":
#checks if there is an opponent piece to the left of the pawn in question
                if [row_location,col_location-1] in opponent_Neighbors:
                    x = col_location - 1
#moves left until it finds a friendly piece or hits the left edge of the board
                    while board[row_location][x] != color and board[row_location][x] != "unoccupied" and x > 0:
                        x = x - 1
#checks if a friendly piece was found or board edge was hit
                    if board[row_location][x] == color:
                        if [row_location,col_location] not in validMoves:
                            validMoves.append((row_location,col_location))
#checks if there is an opponent piece to the right of the pawn in question
                if [row_location,col_location+1] in opponent_Neighbors:
                    x = col_location + 1
#moves right until it finds a friendly piece or hits the right edge of the board
                    while board[row_location][x] != color and board[row_location][x] != "unoccupied" and x < 7:
                        x = x + 1
                    if board[row_location][x] == color:
                        if [row_location,col_location] not in validMoves:
                            validMoves.append((row_location,col_location))
#checks if there is an opponent piece above the pawn in question
                if [row_location-1,col_location] in opponent_Neighbors:
                    y = row_location - 1
#moves up until it finds a friendly piece or hits the top edge of the board
                    while board[y][col_location] != color and board[y][col_location] != "unoccupied" and y > 0:
                        y = y - 1
                    if board[y][col_location] == color:
                        if [row_location,col_location] not in validMoves:
                            validMoves.append((row_location,col_location))
#checks if there is an opponent piece below the pawn in question
                if [row_location+1,col_location] in opponent_Neighbors:
                    y = row_location + 1
#moves down until it finds a friendly piece or hits the bottom edge of the board
                    while board[y][col_location] != color and board[y][col_location] != "unoccupied" and y < 7:
                        y = y + 1
                    if board[y][col_location] == color:
                        if [row_location,col_location] not in validMoves:
                            validMoves.append((row_location,col_location))
#checks if there is an opponent piece above and to the left of the piece in question (diagonally)
                if [row_location-1,col_location-1] in opponent_Neighbors:
                    y = row_location - 1
                    x = col_location - 1
#moves diagonally upward and to the left until it finds a friendly piece or hits the upper left edge of the board
                    while board[y][x] != color and board[y][x] != "unoccupied" and x > 0 and y > 0:
                        x = x - 1
                        y = y - 1
                    if board[y][x] == color:
                        if [row_location,col_location] not in validMoves:
                            validMoves.append((row_location,col_location))
#checks if there is an opponent piece above and to the right of the piece in question (diagonally)
                if [row_location-1,col_location+1] in opponent_Neighbors:
                    y = row_location - 1
                    x = col_location + 1
#moves diagonally upward and to the right until it finds a friendly piece or hits the upper right edge of the board
                    while board[y][x] != color and board[y][x] != "unoccupied" and x < 7 and y > 0:
                        x = x + 1
                        y = y - 1
                    if board[y][x] == color:
                        if [row_location,col_location] not in validMoves:
                            validMoves.append((row_location,col_location))
#checks if there is an opponent piece below and to the left of the piece in question (diagonally)
                if [row_location+1,col_location-1] in opponent_Neighbors:
                    y = row_location + 1
                    x = col_location - 1
#moves diagonally downward and to the left until it finds a friendly piece or hits the bottom left edge of the board
                    while board[y][x] != color and board[y][x] != "unoccupied" and x > 0 and y < 7:
                        x = x - 1
                        y = y + 1
                    if board[y][x] == color:
                        if [row_location,col_location] not in validMoves:
                            validMoves.append((row_location,col_location))
#if there is an opponent piece below and to the right of the piece in question (diagonally)
                if [row_location+1,col_location+1] in opponent_Neighbors:
                    y = row_location + 1
                    x = col_location + 1
#moves diagonally downward and to the right until it finds a friendly piece or hits the bottom right edge of the board
                    while board[y][x] != color and board[y][x] != "unoccupied" and x < 7 and y < 7:
                        x = x + 1
                        y = y + 1
                    if board[y][x] == color:
                        if [row_location,col_location] not in validMoves:
                            validMoves.append((row_location,col_location))
    return validMoves

#boolean function that determines if a given move is valid or not
def isValidMove(board,row,col,color):
    validMoves = getValidMoves(board,color)
    if (row,col) in validMoves:
        return True
    else:
        return False

#generates possible valid computer moves using getValidMoves function and picks a random move from the list obtained
def selectNextPlay(board):
    validMoves = getValidMoves(board,"white")
    num = random.randint(0,len(validMoves)-1)
    move = validMoves[num]
    return move

#simple function that converts a matrix index to a coordinate that will plot correctly when turtle is used
def matrixToTurtle(row,col):
    row = 6.5 - row
    col = col + 0.5
    return row,col

#determines the captured pieces for a given player move and updates both the matrix and the turtle screen
#works in a similar way to getValidMoves by using a list of opponent neighbors to determine a direction in which to search for friendly pieces
#instead of searching each unnocupied board location, however, this function takes a valid move and works backwards to find an already placed friendly piece
def updateBoard(board,row,col,color):
    board[row][col] = color
    opponent_Neighbors = getNeighbors(board,row,col,color)
#adds the move itself to the list of captured pieces so that it is the first point that the turtle plots (helps with visual clarity)
    capturedPieces = [[row,col]]
    turtleMoves = []
    t = turtle.Turtle()
    t.hideturtle()
    t.color(color)
    t.penup()
    t.speed(0)
    if [row,col-1] in opponent_Neighbors:
        x = col - 1
        while board[row][x] != color and board[row][x] != "unoccupied" and x > 0:
            x = x - 1
        if board[row][x] == color:
#unlike getValidMoves, this function will backtrack, using a while loop, when it finds a friendly piece, adding captured pieces to a list as it goes
            while x < col:
                board[row][x] = color
                capturedPieces.append([row,x])
                x = x + 1
    if [row,col+1] in opponent_Neighbors:
        x = col + 1
        while board[row][x] != color and board[row][x] != "unoccupied" and x < 7:
            x = x + 1
        if board[row][x] == color:
            while x > col:
                board[row][x] = color
                capturedPieces.append([row,x])
                x = x - 1
    if [row-1,col] in opponent_Neighbors:
        y = row - 1
        while board[y][col] != color and board[y][col] != "unoccupied" and y > 0:
            y = y - 1
        if board[y][col] == color:
            while y < row:
                board[y][col] = color
                capturedPieces.append([y,col])
                y = y + 1
    if [row+1,col] in opponent_Neighbors:
        y = row + 1
        while board[y][col] != color and board[y][col] != "unoccupied" and y < 7:
            y = y + 1
        if board[y][col] == color:
            while y > row:
                board[y][col] = color
                capturedPieces.append([y,col])
                y = y - 1
    if [row-1,col-1] in opponent_Neighbors:
        x = col - 1
        y = row - 1
        while board[y][x] != color and board[y][x] != "unoccupied" and x > 0 and y > 0:
            x = x - 1
            y = y - 1
        if board[y][x] == color:
            while x < col and y < row:
                board[y][x] = color
                capturedPieces.append([y,x])
                x = x + 1
                y = y + 1
    if [row-1,col+1] in opponent_Neighbors:
        x = col + 1
        y = row - 1
        while board[y][x] != color and board[y][x] != "unoccupied" and x < 7 and y > 0:
            x = x + 1
            y = y - 1
        if board[y][x] == color:
            while x > col and y < row:
                board[y][x] = color
                capturedPieces.append([y,x])
                x = x - 1
                y = y + 1
    if [row+1,col-1] in opponent_Neighbors:
        x = col - 1
        y = row + 1
        while board[y][x] != color and board[y][x] != "unoccupied" and x > 0 and y < 7:
            x = x - 1
            y = y + 1
        if board[y][x] == color:
            while x < col and y > row:
                board[y][x] = color
                capturedPieces.append([y,x])
                x = x + 1
                y = y - 1
    if [row+1,col+1] in opponent_Neighbors:
        x = col + 1
        y = row + 1
        while board[y][x] != color and board[y][x] != "unoccupied" and x < 7 and y < 7:
            x = x + 1
            y = y + 1
        if board[y][x] == color:
            while x > col and y > row:
                board[y][x] = color
                capturedPieces.append([y,x])
                x = x - 1
                y = y - 1
#runs each captured piece (including the move itself) through matrixToTurtle, converting them into coordinates that will plot correctly
    for piece in capturedPieces:
        row = piece[0]
        col = piece[1]
        converted_row,converted_col = matrixToTurtle(row,col)
        turtleMoves.append([converted_row,converted_col])
    for location in turtleMoves:
        t.goto(location[1],location[0])
        t.dot(70)
    return board

def main():
    endedPrematurely = False
#creates board graphic with inital four pieces
    board = createBoard()
#user always gets to make the first move
    color = "black"
    validMoves = getValidMoves(board,color)
#game will end when either player has no valid moves left or the user chooses to end early
    while len(validMoves) != 0 and endedPrematurely == False:
        user_move = str(turtle.textinput("","Enter row,col: "))
#user can exit the game early by entering a null string
        if user_move != "":
            row = int(user_move[0])
            col = int(user_move[2])
#program will keep asking for input until the user chooses a correct move
            while isValidMove(board,row,col,color) == False:
                user_move = str(turtle.textinput(user_move + " is not a valid move.","Enter row,col: "))
                row = int(user_move[0])
                col = int(user_move[2])
            board = updateBoard(board,row,col,color)
#start of computer players turn
            color = "white"
            validMoves = getValidMoves(board,color)
#must recheck the length of validMoves before continuing since validMoves is now attributed to the computer player
#if this parameter were not in place, the computer would be allowed a final turn with no possible moves to make
            if len(validMoves) > 0:
                computer_move = selectNextPlay(board)
                row = int(computer_move[0])
                col = int(computer_move[1])
                board = updateBoard(board,row,col,"white")
                color = "black"
                validMoves = getValidMoves(board,color)
        else:
            endedPrematurely == True
#if the game was not ended early, the program determines a winner (customizing the closing statement accordingly) and prints the final scores
    if endedPrematurely == False:
        turtle.clearscreen()
        turtle.hideturtle()
        turtle.penup()
        turtle.goto(3.5,1)
        userScore = 0
        computerScore = 0
        for row in board:
            for col in row:
                if col == "black":
                    userScore += 1
                elif col == "white":
                    computerScore += 1
        if userScore > computerScore:
            turtle.write("You won!\nYour score: " + str(userScore) +"\nComputer score: " + str(computerScore) + "\nThanks for playing othello!", True, align = "center",font = ("Arial",100,"bold"))
        elif userScore < computerScore:
            turtle.write("Sorry, you lost the game.\nYour score: " + str(userScore) +"\nComputer score: " + str(computerScore) + "\nThanks for playing othello!", True, align = "center",font = ("Arial",100,"bold"))
        elif userScore == computerScore:
            turtle.write("The game ended in a tie.\nYour score: " + str(userScore) +"\nComputer score: " + str(computerScore) + "\nThanks for playing othello!", True, align = "center",font = ("Arial",100,"bold"))
#if the game is ended early, no score will be printed since the game was not completed
    else:
        turtle.clearscreen()
        turtle.bye()

if __name__ == '__main__':
    main()
