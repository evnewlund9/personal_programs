#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include "deltas.h"

//Reads integers in text delta format (as apposed to binary format) and returns an array of them
int *read_text_deltas(char *fname, int *len) {
    FILE *file;
    file = fopen(fname, "r"); //File is opened with reading privledges
    if (file == NULL){ //If the file doesn't exist
      *len = -1;       //the len pointer is dereferenced so that its value can be changed (even though it's defined outside read_deltas),
      return NULL;    //and the function returns a null pointer. It does not attempt to close the file since the file was never sucessfully opened.
    }

    int lastDelta; //Variable that stores previous read integers so that new array indexes (deltas) can be computed.
    int numberOfDeltas = fscanf(file,"%d", &lastDelta); //lastDelta also serves as a vessel for fscanf to store the first integer in the file.
     if (numberOfDeltas != EOF && numberOfDeltas != 0) { //In this way, the if statement can check that the first character in the file is not EOF (in the case of an empty file) and that there are actually int to be read (fscanf will return 0 if no ints are read).
        while (fscanf(file,"%d", &lastDelta) != EOF) { //While loop iterates through the file until it reaches the end, using lastDelta as a place to store the integer values (which it doesn't care about yet).
            numberOfDeltas++; //It counts the number of read integers as it goes so that an array can later be initialized with an appropriate length
        }

        rewind(file); //Now that the function has a length for the array, it must rewind to the beginning of the file and start over, actually using the values this time.
        int *deltas = malloc(numberOfDeltas * sizeof(int)); //Allocates enough space on the heap for an array of ints with the length found previously. Uses sizeof() to verify the size of an int on any given machine.

        fscanf(file, "%d", &deltas[0]); //The first int delta is simply read into the array since there is no preceding int to add its value to.
        for (int i = 1; i < numberOfDeltas; i++){
            fscanf(file, "%d", &lastDelta); //The rest of the ints get read into the variable lastDelta, rather than directly into an array index
            deltas[i] = deltas[i-1] + lastDelta; //Each new int in the array, after the first, is the sum of the previous term and the most recently read delta.
        } //Thus, all integers after the first represent modifications of the first term, not autonomous array indexes.

        *len = numberOfDeltas; //Again, len must be dereferenced since it is a pointer.
        fclose(file); //Once the whole file has been read it is safe (and necessary) to close it.
        return deltas; //Returns a pointer integer that was given enough size with malloc for all the integers in the file (effectively an array).
    }

    else {
      *len = -1; //Just like if the file does not exist, the length must be set to -1 if the file contains no integers or is empty.
      fclose(file); //In this case, however, it is safe to close the file because the file exists and was sucessfully opened.
      return NULL; //File is closed prior to return in both cases.
    }
}

//Reads integers in binary delta format and returns an array of them
int *read_int_deltas(char *fname, int *len){
    struct stat sb; //Provided struct that uses a Unix system call, stat(), to find the size of the file in bytes.
    int result = stat(fname, &sb); //As in read_text_deltas, the program must verify that the file is eligible for reading.
    if(result==-1 || sb.st_size < sizeof(int)){ //This if statement checks tat stat() was succsessful and that the file size is at least as big as a single int (because otherwise there is no point in reading it).
      *len = -1; //As before, if the file is not appropriate for reading, len is set to -1 and a null pointer is returned.
        return NULL;
    }
    int total_bytes = sb.st_size;

    FILE *file; //Since the above struct checks if stat() was succsessful, there is no need to make sure that the file is not null (because if it was stat() would fail and the function would return a null pointer).
    file = fopen(fname, "r");

    int *binary = malloc(total_bytes); //sizeof(int) is not needed as it was in read_text_deltas because the struct convieniently identified the size of the file in bytes.
    fread(&binary[0], sizeof(int), 1, file); //As before, the first delta is simply read into the array.

    for (int i = 1; i < (total_bytes/sizeof(int));i++) { //For loop works the same as before except that the length of the array if computed by dividing the total bytes in the file by the size of an int to get the number of ints.
      fread(&binary[i], sizeof(int), 1, file); //fread() is used instead of fscanf() since fread() works with binary input.
      binary[i] = binary[i] + binary[i-1]; //lastDelta was used in read_text_deltas because it was already defined. In read_int_deltas, there is no such variable. Rather than define one unnesesarily,
    }                                     //the function simply reads into the given index before modifying that value by adding the value in the previous index.

    *len = total_bytes/sizeof(int); //As before, the length is calculated by dividing the total bytes in the file by the size of an int.
    fclose(file);
    return binary; //The returned array, whether it is in read_text_deltas or read_int_deltas, is free()'d in deltas_main, so there is no need to free them here.
  } //Note that the optional read_4bit_deltas is not implemented in this file.
