import turtle as t
import random

def life():
    m = []
    for i in range(100):
        m.append([0]*100)
    x = 1
    while x <= 1000:
        m[random.randint(30,70)][random.randint(30,70)] = 1
        x += 1
    return m

def neighbors(m,row,column):
    isOrganism = False
    neighbor_count = 0
    row = "".join(str(row))
    column = "".join(str(column))
    row_number = row.find(row)
    column_number = row.find(column)
    if m[row_number-1][column_number-1] == 1:
        neighbor_count += 1
    if m[row_number-1][column_number] == 1:
        neighbor_count += 1
    if m[row_number-1][column_number+1] == 1:
        neighbor_count += 1
    if m[row_number][column_number-1] == 1:
        neighbor_count += 1
    if m[row_number][column_number+1] == 1:
        neighbor_count += 1
    if m[row_number+1][column_number-1] == 1:
        neighbor_count += 1
    if m[row_number+1][column_number] == 1:
        neighbor_count += 1
    if m[row_number+1][column_number+1] == 1:
        neighbor_count += 1
    if neighbor_count == 2 or neighbor_count == 3:
        isOrganism = True
    else:
        isOrganism = False
    return isOrganism

def update(m,row,column):
    matrix = []
    for i in range(100):
        matrix.append([0]*100)
    for row in m:
        for column in m:
            if column == 1:
                matrix[row][column] = 1
    return matrix


def main():
    t.Turtle
    t.speed(0)
    t.penup()
    t.setworldcoordinates(0.00,0.00,100.00,100.00)
    m = life()
    matrix = m
    x = 1
    while x <= 50:
        for row in range(1,100):
            for column in range(1,100):
                row = "".join(str(row))
                column = "".join(str(column))
                row_number = row.find(row)
                column_number = row.find(column)
                neighbor_count = neighbors(m,row,column)
                if isOrganism == True:
                    matrix[row_number][column_number] == 1
                else:
                    matrix[row_number][column_number] == 0
                t.setposition(row_number,column_number)
                t.pendown()
                t.dot()
    turtle.done()



if __name__=="__main__":
    main()
