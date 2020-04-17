#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hashmap.h"

//Much of hash_main was derived from list_main in lab 2.
//Accepts user input and calls the appropriate hashmap functions.
int main(int argc, char *argv[]){

  int echo = 0;
  if(argc > 1 && strcmp("-echo",argv[1])==0) { //Allows user to echo their commands by adding a 1 as an argument when running main.
    echo=1;
  }

  hashmap_t hashmap;
  hashmap_init(&hashmap,HASHMAP_DEFAULT_TABLE_SIZE); //Initializes a hashmap with the default table size (5) for use in the rest of main.

  //Displays information about the available hashmap commands and their structures.
  printf("Hashmap Demo\n");
  printf("Commands:\n");
  printf("  hashcode <key>   : prints out the numeric hash code for the given key (does not change the hash map)\n");
  printf("  put <key> <val>  : inserts the given key/val into the hash map, overwrites existing values if present\n");
  printf("  get <key>        : prints the value associated with the given key or NOT FOUND\n");
  printf("  print            : shows contents of the hashmap ordered by how they appear in the table\n");
  printf("  structure        : prints detailed structure of the hash map\n");
  printf("  clear            : reinitializes hash map to be empty with default size\n");
  printf("  save <file>      : writes the contents of the hash map the given file\n");
  printf("  load <file>      : clears the current hash map and loads the one in the given file\n");
  printf("  next_prime <int> : if <int> is prime, prints it, otherwise finds the next prime and prints it\n");
  printf("  expand           : expands memory size of hashmap to reduce its load factor\n");
  printf("  quit             : exit the program\n");

  while(1){ //Infinite loop is okay because "quit" command will break out of the loop if necessary.
    printf("HM> ");
    int success;
    char cmd[128]; //Array to hold command string is initialized as very large, just in case, to prevent segmentation faults.
    success = fscanf(stdin,"%s",cmd); //Reads the first word the user entered when prompted.
    if(success==EOF){ //Checks that scanf() was able to succsessfully read the string, breaks out of the loop if this is not the case.
      printf("\n");
      break;
    }

    //Handles case where the command is "quit."
    if( strcmp("quit", cmd)==0){
      if(echo){
        printf("quit\n");
      }
      break; //Simply breaks the loop in this case.
    }

    //Handles case where the command is "hashcode."
    else if(strcmp("hashcode", cmd)==0){
      char key[128]; //Makes array to be read into quite large, just to be safe.
      fscanf(stdin,"%s",key); //Scans the second word the user entered. Can be sure the user entered a second word because the first is "hashcode" which requires a key afterwards.

      if(echo){
        printf("hashcode %s\n",key);
      }

      long hash = hashcode(key); //Uses provided hashcode() function.
      printf("%ld\n", hash);
    }

    //Handles case where the command is "put."
    else if(strcmp("put", cmd)==0){
      char key[128]; //Works the same as before (reading into an array), but with a second array, value, this time.
      fscanf(stdin,"%s",key);
      char value[128];
      fscanf(stdin,"%s",value); //The user must enter two words, a key and a value, after a "put" command.

      if(echo){
        printf("put %s %s\n",key,value);
      }

      int result;
      result = hashmap_put(&hashmap,key,value);
      if (result == 0){ //Checks if a new node was added or if another node with the same key exists and was overwritten.
        printf("Overwriting previous key/val\n");
      }
    }

    //Handles case where the command is "get."
    else if( strcmp("get", cmd)==0 ){
      char key[128];
      fscanf(stdin,"%s",key);

      if(echo){
        printf("get %s\n",key);
      }

      char *ith = hashmap_get(&hashmap, key);
      if(ith == NULL){ //If hashmap_get fails to find a node with the specified key, it returns a null pointer.
        printf("NOT FOUND\n");
      }
      else { //If hashmap_get is succsessful in finding a node with the specified key, it will return the assosiated value.
        printf("FOUND: %s\n",ith);
      }
    }

    //Handles case where the command is "print."
    else if (strcmp("print",cmd)==0) {
      if(echo){
        printf("print\n");
      }
      hashmap_write_items(&hashmap, stdout); //Calls hashmap_write_items, which can be used to print to files as well. In this case, it is used to print to the terminal (stdout).
    }

    //Handles case where the command is "structure."
    else if (strcmp("structure",cmd)==0) {
      if(echo){
        printf("structure\n");
      }
      hashmap_show_structure(&hashmap);
    }

    //Handles case where the command is "clear."
    else if( strcmp("clear", cmd)==0 ){
      if(echo){
        printf("clear\n");
      }
      hashmap_free_table(&hashmap); //First, uses hashmap_free_table to deallocate all the fields of the hashmap...
      hashmap_init(&hashmap,HASHMAP_DEFAULT_TABLE_SIZE); //...and then Initializes a new one with the same pointer to it and with the default size.
    }

    //Handles case where the command is "save."
    else if (strcmp("save", cmd)==0 ){
      char filename[128];
      fscanf(stdin,"%s",filename); //Filename must be specified as path, not just the name of the file itself.

      if(echo){
        printf("save %s\n",filename);
      }

      hashmap_save(&hashmap, filename);
    }

    //Handles case where the command is "load."
    else if (strcmp("load",cmd) == 0) {
      char filename[128];
      fscanf(stdin,"%s",filename);

      if(echo){
        printf("load %s\n",filename);
      }

      hashmap_load(&hashmap, filename);
    }

    //Handles case where the command is "next_prime."
    else if (strcmp("next_prime",cmd) == 0) {
      char num[128];
      fscanf(stdin,"%s",num);

      if(echo){
        printf("next_prime %s\n",num);
      }

      int result;
      int n;
      n = atoi(num); //Uses atoi() again to convert the string to an integer.
      result = next_prime(n);
      printf("%d\n", result);
    }

    //Handles case where the command is "expand."
    else if (strcmp("expand",cmd) == 0) {
      if(echo){
        printf("expand\n");
      }

      hashmap_expand(&hashmap);
    }

    //Handles case where the command is none of the possible options.
    else{
      if(echo){
        printf("%s\n",cmd);
      }
      printf("unknown command %s\n",cmd);
    }
  }

  hashmap_free_table(&hashmap); //Once the while loop is terminated, the memory used by the hashmap is free()'d.
  return 0;
}
