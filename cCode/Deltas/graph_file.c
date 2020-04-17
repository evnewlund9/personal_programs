#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "deltas.h"

//Most of main copied from deltas_main.
int main(int argc, char *argv[]){ //Main arguments written in a way that accepts command line arguments.

  char *format = argv[1]; //Specifies if file is in text or int format.
  char *fname = argv[2]; //Name of the file.
  int max_height = atoi(argv[3]); //Utilizes atoi() function to convert string to integer

  int data_len;
  data_len = -1; //Data len is initialized to -1 and must be overwritten in read_deltas
  int *data_vals = NULL; //Initializes pointer which will eventually point to the array of deltas

  //Handles the case of a text file.
  if( strcmp("text", format)==0 ){ //Uses strcmp() to check if format string is equivalent to the string "text."
    printf("Reading text format\n");
    data_vals = read_text_deltas(fname, &data_len); //If the format is text, the appropriate read_deltas function is called.
  }

  //Handles the case of a binary int file.
  else if( strcmp("int", format)==0 ){
    printf("Reading binary int format\n");
    data_vals = read_int_deltas(fname, &data_len);
  }

  //Handles the case that the second command line argument is not "text" or "int."
  else{
    printf("Unknown format '%s'\n",format);
    return 1; //If the format is unknown and the file cannot be read, print_graph is never called.
  }

  print_graph(data_vals,data_len,max_height);

  free(data_vals); //Now that the array has fufilled its purpose, it can be free()'d.

  return 0;
}
