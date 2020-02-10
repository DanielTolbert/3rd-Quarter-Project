cmake_minimum_required(VERSION 3.15)
project(Wiimote_Interfacing C)

set(CMAKE_C_STANDARD 99)

message(STATUS "start running cmake...")

find_package()

add_executable(Wiimote_Interfacing main.c)