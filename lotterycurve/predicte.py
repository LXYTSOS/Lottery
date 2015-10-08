__author__ = 'sl169'

import numpy as np

def prediction( num):
    N = len(num)

    b = num[-N:]
    b = b[::-1]

    A = np.zeros((N, N), int)

    for i in range(N):
        A[i,]=num
        num1=[]
        num1.append(num[N-1])
        for j in range(N-1):
            num1.append(num[j])
        num=num1



    (x, residuals, rank, s) = np.linalg.lstsq(A,b)
    # print x, residuals, rank, s
    return np.dot(b,x)