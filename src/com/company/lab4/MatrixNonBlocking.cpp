/* FILE: mpi_mm.c DESCRIPTION: MPI Matrix Multiply - C Version
* In this code, the master task distributes a matrix multiply
* operation to numtasks-1 worker tasks.
* AUTHOR: Blaise Barney.
* Adapted from Ros Leibensperger, Cornell Theory
*/

#include "mpi.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <mpi.h>

#define NRA 6200        /* number of rows in matrix A */
#define NCA 15             /* number of columns in matrix A */
#define NCB 70             /* number of columns in matrix B */
#define MASTER 0         /* taskid of first task */
#define FROM_MASTER 1 /* setting a message type */
#define FROM_WORKER 2 /* setting a message type */

int main(int argc, char *argv[]) {
    double start = MPI_Wtime() * 1000;

    int numtasks, taskid, numworkers, source, dest, rows,        /* rows of matrix A sent to each worker */
    averow, extra, offset, i, j, k, rc;
    double a[NRA][NCA], /* matrix A to be multiplied */
    b[NCA][NCB], /* matrix B to be multiplied */
    c[NRA][NCB]; /* result matrix C */
    MPI_Status status;
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &numtasks);
    MPI_Comm_rank(MPI_COMM_WORLD, &taskid);
    if (numtasks < 2) {
        printf("Need at least two MPI tasks. Quitting...\n");
        MPI_Abort(MPI_COMM_WORLD, rc);
        exit(1);
    }
    numworkers = numtasks - 1;
    if (taskid == MASTER) {
        printf("mpi_mm has started with %d tasks.\n", numtasks);
        for (i = 0; i < NRA; i++)
            for (j = 0; j < NCA; j++)
                a[i][j] = 10;
        for (i = 0; i < NCA; i++)
            for (j = 0; j < NCB; j++)
                b[i][j] = 10;

        averow = NRA / numworkers;
        extra = NRA % numworkers;
        offset = 0;
        for (dest = 1; dest <= numworkers; dest++) {
            rows = (dest <= extra) ? averow + 1 : averow;
            printf("Sending %d rows to task %d offset=%d\n", rows, dest, offset);

            MPI_Request send_req;

            MPI_Isend(&offset, 1, MPI_INT, dest, FROM_MASTER, MPI_COMM_WORLD, &send_req);
            // sleep(5); /* Процес 0 виконує дії, які не змінюють data */
            MPI_Wait(&send_req, MPI_STATUS_IGNORE);

            MPI_Isend(&rows, 1, MPI_INT, dest, FROM_MASTER, MPI_COMM_WORLD, &send_req);
            // sleep(5); /* Процес 0 виконує дії, які не змінюють data */
            MPI_Wait(&send_req, MPI_STATUS_IGNORE);

            MPI_Isend(&a[offset][0], rows * NCA, MPI_DOUBLE, dest, FROM_MASTER, MPI_COMM_WORLD, &send_req);
            // sleep(5); /* Процес 0 виконує дії, які не змінюють data */
            MPI_Wait(&send_req, MPI_STATUS_IGNORE);

            MPI_Isend(&b, NCA * NCB, MPI_DOUBLE, dest, FROM_MASTER, MPI_COMM_WORLD, &send_req);
            // sleep(5); /* Процес 0 виконує дії, які не змінюють data */
            MPI_Wait(&send_req, MPI_STATUS_IGNORE);

            offset = offset + rows;
        }
        /* Receive results from worker tasks */
        for (source = 1; source <= numworkers; source++) {
            MPI_Request recv_req;

            MPI_Irecv(&offset, 1, MPI_INT, source, FROM_WORKER, MPI_COMM_WORLD, &recv_req);
            // sleep(2); /* Процес 1 виконує дії, які не використовують data */
            MPI_Wait(&recv_req, MPI_STATUS_IGNORE);

            MPI_Irecv(&rows, 1, MPI_INT, source, FROM_WORKER, MPI_COMM_WORLD, &recv_req);
            // sleep(2); /* Процес 1 виконує дії, які не використовують data */
            MPI_Wait(&recv_req, MPI_STATUS_IGNORE);

            MPI_Irecv(&c[offset][0], rows * NCB, MPI_DOUBLE, source, FROM_WORKER, MPI_COMM_WORLD, &recv_req);
            // sleep(2); /* Процес 1 виконує дії, які не використовують data */
            MPI_Wait(&recv_req, MPI_STATUS_IGNORE);

            printf("Received results from task %d\n", source);
        }
        /* Print results */
        printf("****\n");
        printf("Result Matrix:\n");
        for (i = 0; i < NRA; i++) {
            printf("\n");
            for (j = 0; j < NCB; j++)
                printf("%6.2f ", c[i][j]);
        }
        printf("\n********\n");
        printf("Done.\n");
        double end = MPI_Wtime() * 1000;
        printf("Work took. %f milliseconds \n", (end - start));
    }
        /******** worker task *****************/
    else { /* if (taskid > MASTER) */
        MPI_Request recv_req;

        MPI_Irecv(&offset, 1, MPI_INT, MASTER, FROM_MASTER, MPI_COMM_WORLD, &recv_req);
        // sleep(2); /* Процес 1 виконує дії, які не використовують data */
        MPI_Wait(&recv_req, MPI_STATUS_IGNORE);

        MPI_Irecv(&rows, 1, MPI_INT, MASTER, FROM_MASTER, MPI_COMM_WORLD, &recv_req);
        // sleep(2); /* Процес 1 виконує дії, які не використовують data */
        MPI_Wait(&recv_req, MPI_STATUS_IGNORE);

        MPI_Irecv(&a, rows * NCA, MPI_DOUBLE, MASTER, FROM_MASTER, MPI_COMM_WORLD, &recv_req);
        // sleep(2); /* Процес 1 виконує дії, які не використовують data */
        MPI_Wait(&recv_req, MPI_STATUS_IGNORE);

        MPI_Irecv(&b, NCA * NCB, MPI_DOUBLE, MASTER, FROM_MASTER, MPI_COMM_WORLD, &recv_req);
        // sleep(2); /* Процес 1 виконує дії, які не використовують data */
        MPI_Wait(&recv_req, MPI_STATUS_IGNORE);

        for (k = 0; k < NCB; k++)
            for (i = 0; i < rows; i++) {
                c[i][k] = 0.0;
                for (j = 0; j < NCA; j++)
                    c[i][k] = c[i][k] + a[i][j] * b[j][k];
            }

        MPI_Request send_req;

        MPI_Isend(&offset, 1, MPI_INT, MASTER, FROM_WORKER, MPI_COMM_WORLD, &send_req);
        // sleep(5); /* Процес 0 виконує дії, які не змінюють data */
        MPI_Wait(&send_req, MPI_STATUS_IGNORE);

        MPI_Isend(&rows, 1, MPI_INT, MASTER, FROM_WORKER, MPI_COMM_WORLD, &send_req);
        // sleep(5); /* Процес 0 виконує дії, які не змінюють data */
        MPI_Wait(&send_req, MPI_STATUS_IGNORE);

        MPI_Isend(&c, rows * NCB, MPI_DOUBLE, MASTER, FROM_WORKER, MPI_COMM_WORLD, &send_req);
        // sleep(5); /* Процес 0 виконує дії, які не змінюють data */
        MPI_Wait(&send_req, MPI_STATUS_IGNORE);
    }
    MPI_Finalize();
}

// mpicc MatrixNonBlocking.cpp -o MatrixNonBlocking.o && mpirun -np 4 MatrixNonBlocking.o
