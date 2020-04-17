#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include "hashmap.h"

//Initializes a new hashmap with a given table size.
void hashmap_init(hashmap_t *hm, int table_size){
  hm->item_count = 0; //A new hashmap has, obviously, no items in it.
  hm->table_size = table_size;
  hm->table = malloc(table_size * sizeof(hashnode_t*)); //Finds enough space on the heap for an array of size table_size.
  for (int i = 0; i < table_size; i++) {
    hm->table[i] = NULL; //Initializes each value of the table as a null pointer.
  }
}

//Given function that converts a string to a number.
long hashcode(char key[]) {
    union {
        char str[8];
        long num;
    } strnum;
    strnum.num = 0;

    for(int i=0; i<8; i++){
        if(key[i] == '\0'){
        break;
        }
        strnum.str[i] = key[i];
    }
    return strnum.num;
}

//Function that puts a new node into the hashmap. If a node alread exists with the specifed key, the data is simply copied over.
int hashmap_put(hashmap_t *hm, char key[], char val[]){
  long position = hashcode(key) % hm->table_size; //Given eqution for finding the position in the table.
  hashnode_t *newNode = malloc(sizeof(hashnode_t)); //Finds space on the heap for a new node.
  strcpy(newNode->key,key); //Copies the specified key and value to the new node.
  strcpy(newNode->val,val);
  hashnode_t *ptr = hm->table[position]; //Points to the location the equation indicated.
  if (ptr == NULL) { //If there is no node there, the node is simply added at that location.
    hm->table[position] = newNode;
    newNode->next = ptr; //Sets the "next" field of the poointer to NULL.
    hm->item_count++;
    return 1; //If a node is sucessfully added (without overwritting another), the function returns 1;
  }

  else { //If there is node in that location, the function iterates through the list to see if there's a node with the same key.
    while (1) { //Infinite loop is fine because second if statement makes sure ptr is never NULL.
      if (strcmp(ptr->key,key)==0){ //If a node already exists at the given location with the same key, the key and val of the new node are simply copied over and the  memory for the new node if free()'d.
        strcpy(ptr->val,val);
        free(newNode);
        return 0; //If a node isn't added, but is rewritten instead, the function returns 0.
      }
      if (ptr->next == NULL) { //Makes sure that ptr never becomes NULL.
        break;
      }
      ptr = ptr->next;
    }

    ptr->next = newNode; //If none of the nodes have the same key, the node is added to the end of the list.
    newNode->next = NULL; //The new node, then, points to NULL.
    hm->item_count++;
    return 1;
  }
}

//Gets the value associated with a specified key and returns it.
char *hashmap_get(hashmap_t *hm, char key[]){
  int position = hashcode(key) % hm->table_size; //Uses the same equation as put().
  hashnode_t *ptr = hm->table[position];
  if (ptr == NULL) { //If no nodes exist at the location, the function automatically returns NULL,
    return NULL;
  }
  else { //If there is a node(s) at the location, the function iterates through the list until it finds a node with the specified key.
    while (ptr != NULL) {
      if (strcmp(key,ptr->key) == 0) { //Uses strcpy() to check equivalency.
        return ptr->val;
      }
      ptr = ptr->next;
    }
  return NULL; //If no node is found with the right key, the function returns null.
  }
}

//Writes the items of the current hashmap to a given file stream.
void hashmap_write_items(hashmap_t *hm, FILE *out){
    hashnode_t *ptr;
    for (int i = 0; i < hm->table_size; i++) { //Iterates through each spot in the table.
      ptr = hm->table[i];
      while (ptr != NULL) { //Iterates through all nodes at a given table index.
        fprintf(out, "%12s : %s\n",ptr->key,ptr->val);
        ptr = ptr->next;
      }
    }
  }

//Prints a more detailed version of the hashmap, but only to the terminal.
void hashmap_show_structure(hashmap_t *hm) {
  printf("item_count: %d\n",hm->item_count); //Prints the table size and number of items in the table before anything else.
  printf("table_size: %d\n",hm->table_size);
  printf("load_factor: %1.4f\n",((float) hm->item_count/hm->table_size)); //Includes a new "load factor" variable.
  hashnode_t *ptr;
  for (int i = 0; i < hm->table_size; i++) {
    printf("%3d : ",i); //Prints each index in a right-justified field of three characters.
    ptr = hm->table[i];
    while (ptr != NULL) {
      printf("{(%2ld) %s : %s} ",hashcode(ptr->key),ptr->key,ptr->val); //Prints the hashcode, key, and value of each node. Chains collided nodes together with a space in between.
      ptr = ptr->next;
    }
    printf("\n");
  }
}

//Works through the hashmap, deallocating all fields as it goes (except for the hashmap itself).
void hashmap_free_table(hashmap_t *hm){
  for (int i = 0; i < hm->table_size; i++){
    hashnode_t *ptr = hm->table[i];
    hashnode_t *temp;
    while (ptr != NULL) {
      temp = ptr; //A temporary variable is required so that each node can be deallocated without losing the essential "next" field which keeps the function progressing through the list.
      ptr = ptr->next;
      free(temp);
    }
    hm->table[i] = NULL; //Each index of the table is set to NULL.
  }
  free(hm->table); //After all the nodes are free()'d, the table itself is deallocated and the item count is set to zero.
  hm->item_count = 0;
}

//Writes the current hashmap to a specified file using hashmap_write_items.
void hashmap_save(hashmap_t *hm, char *filename) {
  FILE *file;
  file = fopen(filename,"w");

  if (file != NULL) { //Makes sure that the file exists.
    fprintf(file,"%d %d\n ",hm->table_size,hm->item_count); //Prints the table size and item count before calling hashmap_write_items (since that function doesn't do these things).
    hashmap_write_items(hm,file);
    fclose(file); //Makes sure to close the file afterwards.
  }

  else{
    printf("Could not open file.\n");
  }
}

//Replaces the current hashmap with a new one with fields specifed by a specified file.
int hashmap_load(hashmap_t *hm, char *filename){
  FILE *file;
  file = fopen(filename,"r");
  if (file == NULL){
    printf("ERROR: could not open file '%s'\n",filename);
    printf("load failed\n");
    return 0;
  }

  else {
    hashmap_free_table(hm); //Must free current table first to remove all the fields.
    int table_size;
    int item_count;
    fscanf(file,"%d", &table_size); //Scans the table size and item count first so that they can be used in the for loop below.
    fscanf(file,"%d", &item_count);
    hashmap_init(hm,table_size); //Makes a new hashmap with enough size for the table size specified by the file.
    for (int i = 0; i < item_count; i++){ //Stops when i = item_count, not table_size, because a table can have more than one node in an index.
      char key [128];
      char value[128];
      fscanf(file,"%s : %s",key,value);

      hashmap_put(hm,key,value); //Uses put function to add each node copied from the file.
    }
    fclose(file);
    return 0;
  }
}

//Function that returns the next prime number after a specified number.
//Used by expand to optimize hashmap efficiency (since hashmaps with tables of prime number length work better).
int next_prime(int num){
  while (1) {
    int prime = 1; //Assumes that num is prime.
    for (int i = 2; i < (num / 2); i++) { //Checks if any number between 2 and (num/2) divide num.
      if (num % i == 0) {
        prime = 0; //If any number between 2 and (num/2) divide it, then num is not prime.
      }
    }
    if (prime) {
      return num;
    }
    else if (num % 2 == 0) { //If num is not prime and even, the function adds 1 to make num odd and tries again.
      num = num + 1;
    }
    else { //If num is not prime and odd, the function adds 2 to keep num odd and tries again.
      num = num + 2;
    }
  }
}

//Allocates a new, larger hashmap based on the size of the previous hashmap.
void hashmap_expand(hashmap_t *hm){
  hashmap_t temp; //Needs temporary variable so that the current hashmap pointer can be switched to temp and the old hashmap can be deallocated.
  hashmap_init(&temp,next_prime(2*hm->table_size+1)); //Uses the equation for table size given in the instructions.

  for (int i = 0; i < hm->table_size; i++) { //Iterates through each table index, adding all nodes there to the new hashmap.
    hashnode_t *ptr = hm->table[i];
    while(ptr != NULL) {
      hashmap_put(&temp,ptr->key,ptr->val); //Uses put() rather than reuse code.
      ptr = ptr->next;
    }
  }
  hashmap_free_table(hm); //Frees the old hashmap and...
  *hm = temp;            //...sets the value of the pointer to the new hashmap.
}
