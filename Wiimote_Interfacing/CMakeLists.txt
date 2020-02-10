cmake_minimum_required(VERSION 3.15)
project(Wiimote_Interfacing C)
list(APPEND CMAKE_MODULE_PATH "${CMAKE_CURRENT_SOURCE_DIR}/cmake")
set(CMAKE_C_STANDARD 99)

add_executable(Wiimote_Interfacing main.c)