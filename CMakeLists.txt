cmake_minimum_required(VERSION 3.24)
project(Labs)

set(CMAKE_CXX_STANDARD 14)

include_directories(src/com/company/lab4)

add_executable(Labs
        src/com/company/lab4/Lab3.cpp
        src/com/company/lab4/Lab3.h src/com/company/lab4/MatrixNonBlocking.cpp src/com/company/lab4/MatrixNonBlocking.cpp)
