import Wii

def main():

    print("Hello Timothy!")
    sysmenu = Wii.NUS.download(0x000000010000002, 289)
    sysmenu[0] = open("patch.bin", "rb").read()
    sysmenu.tmd.setTitleID(0x0000000100000003)
    sysmenu.tik.setTitleID(0x0000000100000003)
    sysmenu.dumpFile("patched.wad")

if __name__ == "__main__":
    main()