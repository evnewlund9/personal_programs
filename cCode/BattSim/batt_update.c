#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "batt.h"


int set_batt_from_ports(batt_t *batt) {
  if (BATT_VOLTAGE_PORT < 0) { //If voltage is less than zero, the battery has been wired wrong.
    return 1;
  }
  batt->volts = BATT_VOLTAGE_PORT;

  int percent = (batt->volts - 3000) / 8;
  if (percent >= 100) { //Special case: If the formula returns a percentage greater than 100, the number is rounded down (since a battery cannot be more than 100% full).
    batt->percent = 100;
  }
  else if (percent < 0) { //Special case: If the formula returns a negative percentage, the number is brought up to zero.
    batt->percent = 0;
  }
  else {
    batt->percent = percent;
  }

  batt->mode = BATT_STATUS_PORT & 0b1; //Only checks the last bit in the BATT_STATUS_PORT bitstring.
  return 0;
}

int set_display_from_batt(batt_t batt, int *display) {
  unsigned char intStrings[10] = {0b0111111,0b0000011,0b1101101,0b1100111,0b1010011,0b1110110,0b1111110,0b0100011,0b1111111,0b1110111};
  int numbers[3]; //Array where the bitstrings for the three display numbers will be stored.

  //First set of if/else statements handle the case where the display is in voltage mode. Conditionals are combined using && to avoid nesting.
  //First two numbers are created by simply dividing the voltage to convert mV to V. The last digit requires special attention because of rounding.
  if (!batt.mode) {
    numbers[0] = intStrings[(batt.volts / 1000)];
    numbers[1] = intStrings[(batt.volts % 1000) / 100];
  }
  if (!batt.mode && BATT_VOLTAGE_PORT % 10 < 5) {
    numbers[2] = intStrings[(batt.volts % 100) / 10];
    }
  else if (!batt.mode){ //If the remaining voltage in the hundreds spot is greater than or equal to 0.05 V, then the number in the tens spot is rounded up.
    numbers[2] = intStrings[((batt.volts % 100) / 10) + 1];
    }

  //Second set of if/else statments handle the case where the display is in percentage mode.
  //Since the display shouldn't print leading zeroes, this section is split up into cases where the percentage number is either 3,2, or 1, digits.
  if (batt.mode && batt.percent == 100) { //Only case where percentage is 3 digits long.
      numbers[0] = 0b0000011;
      numbers[1] = 0b0111111;
      numbers[2] = 0b0111111;
  }
  else if (batt.mode && batt.percent < 10){ //Case where percentage is 1 digit long. Zeroes out the first 2 bitstrings so that no leading zeroes are displayed in the final product.
      numbers[0] = 0b0000000;
      numbers[1] = 0b0000000;
      numbers[2] = intStrings[batt.percent / 1];
  }
  else if (batt.mode){
    numbers[0] = 0b0000000;
    numbers[1] = intStrings[batt.percent / 10];
    numbers[2] = intStrings[batt.percent % 10];
  }

  //Block of if/else statements that handle the last section of the final bitstring: the battery display.
  //Simply breaks up each of the battery level cases and highlights the corresponding bits for a given percentage.
  //Creates the variable "mask" which will continually expand to accomodate new sections, working backwards, until the final 32 bit length is reached.
  int mask = 0b00000;
  if (batt.percent >= 90) {
    mask = mask | 0b11111;
  }
  else if (batt.percent < 90 && batt.percent >= 70) {
    mask = mask | 0b11110;
  }
  else if (batt.percent < 80 && batt.percent >= 50) {
    mask = mask | 0b11100;
  }
  else if (batt.percent < 50 && batt.percent >= 30) {
    mask = mask | 0b11000;
  }
  else if (batt.percent < 30 && batt.percent >= 5) {
    mask = mask | 0b10000;
  }

  //Handles decimal and voltage/percentage indicators based on the display mode.
  mask = mask << 3;
  if (!batt.mode) {
    mask = mask | 0b00000011;
  }
  else {
    mask = mask | 0b00000100;
  }

  //Handles the last 21 bits by adding each bitstring corresponding the three numbers by working in reverse.
  for (int i = 0; i < 3; i++) {
    mask = mask << 7; //With rach left shift of 7 bits, space for 7 more bits on the leftmost side is created for the next number to be added into.
    mask = mask | (numbers[i]);
  }

  *display = mask; //Sets display equal to, the now complete, bit mask.
  return 0;
}

int batt_update() {
  batt_t batt = {0,0,0};
  int result = set_batt_from_ports(&batt);
  if (result) { //Makes sure that set_batt_from_ports was sucessful in updating the previously blank batt struct.
    return 1;
  }
  set_display_from_batt(batt,&BATT_DISPLAY_PORT);
  return 0;
}
