# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.15

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2019.3.3\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2019.3.3\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug

# Include any dependencies generated for this target.
include example/CMakeFiles/wiiuseexample.dir/depend.make

# Include the progress variables for this target.
include example/CMakeFiles/wiiuseexample.dir/progress.make

# Include the compile flags for this target's objects.
include example/CMakeFiles/wiiuseexample.dir/flags.make

example/CMakeFiles/wiiuseexample.dir/example.c.obj: example/CMakeFiles/wiiuseexample.dir/flags.make
example/CMakeFiles/wiiuseexample.dir/example.c.obj: example/CMakeFiles/wiiuseexample.dir/includes_C.rsp
example/CMakeFiles/wiiuseexample.dir/example.c.obj: ../example/example.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object example/CMakeFiles/wiiuseexample.dir/example.c.obj"
	cd /d C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug\example && C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\wiiuseexample.dir\example.c.obj   -c C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\example\example.c

example/CMakeFiles/wiiuseexample.dir/example.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/wiiuseexample.dir/example.c.i"
	cd /d C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug\example && C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\example\example.c > CMakeFiles\wiiuseexample.dir\example.c.i

example/CMakeFiles/wiiuseexample.dir/example.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/wiiuseexample.dir/example.c.s"
	cd /d C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug\example && C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\example\example.c -o CMakeFiles\wiiuseexample.dir\example.c.s

# Object files for target wiiuseexample
wiiuseexample_OBJECTS = \
"CMakeFiles/wiiuseexample.dir/example.c.obj"

# External object files for target wiiuseexample
wiiuseexample_EXTERNAL_OBJECTS =

example/wiiuseexample.exe: example/CMakeFiles/wiiuseexample.dir/example.c.obj
example/wiiuseexample.exe: example/CMakeFiles/wiiuseexample.dir/build.make
example/wiiuseexample.exe: src/libwiiuse_debug.dll.a
example/wiiuseexample.exe: C:/Program\ Files\ (x86)/mingw-w64/i686-8.1.0-posix-dwarf-rt_v6-rev0/mingw32/i686-w64-mingw32/lib/libhid.a
example/wiiuseexample.exe: C:/Program\ Files\ (x86)/mingw-w64/i686-8.1.0-posix-dwarf-rt_v6-rev0/mingw32/i686-w64-mingw32/lib/libsetupapi.a
example/wiiuseexample.exe: example/CMakeFiles/wiiuseexample.dir/linklibs.rsp
example/wiiuseexample.exe: example/CMakeFiles/wiiuseexample.dir/objects1.rsp
example/wiiuseexample.exe: example/CMakeFiles/wiiuseexample.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable wiiuseexample.exe"
	cd /d C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug\example && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\wiiuseexample.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
example/CMakeFiles/wiiuseexample.dir/build: example/wiiuseexample.exe

.PHONY : example/CMakeFiles/wiiuseexample.dir/build

example/CMakeFiles/wiiuseexample.dir/clean:
	cd /d C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug\example && $(CMAKE_COMMAND) -P CMakeFiles\wiiuseexample.dir\cmake_clean.cmake
.PHONY : example/CMakeFiles/wiiuseexample.dir/clean

example/CMakeFiles/wiiuseexample.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\example C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug\example C:\Users\jmz00\Git\4th-Year-Project\3rd-Quarter-Project\wiiuse-master\cmake-build-debug\example\CMakeFiles\wiiuseexample.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : example/CMakeFiles/wiiuseexample.dir/depend

