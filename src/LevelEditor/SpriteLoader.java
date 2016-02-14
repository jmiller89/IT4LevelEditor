//This is used to load sprites

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */

//import java.util.ArrayList;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
//import javax.imageio.spi.IIORegistry;
//import javax.imageio.spi.ImageReaderSpi;

public class SpriteLoader
{
    //private ArrayList<Sprite> sprites;
    private Sprite[] sprites;

    //Update this every time a sprite is added
    public static final int MAX_TILE_INDEX = 391;

    public SpriteLoader()
    {
        //sprites = new ArrayList<Sprite>();
        sprites = new Sprite[MAX_TILE_INDEX + 1];
    }

    public Sprite getSprite(int id)
    {

        if (id >= 0)
        {
//            for(int i = 0; i < sprites.size(); i++)
//            {
//                if (sprites.get(i).getID() == id)
//                {
//                    return sprites.get(i);
//                }
//            }

            if (sprites[id] != null)
            {
                return sprites[id];
            }

            String ref = getRef(id);


            BufferedImage source = null;

            try
            {
                // The ClassLoader.getResource() ensures we get the sprite

                // from the appropriate place, this helps with deploying the game

                // with things like webstart. You could equally do a file look

                // up here.

                
                URL url = this.getClass().getClassLoader().getResource(ref);

                if (url == null)
                {
                    System.out.println("Bad filename : ref = " + ref);
                }

                // use ImageIO to read the image in

                source = ImageIO.read(url);
                //source = ImageIO.read(getClass().getClassLoader().getResourceAsStream(ref));


            }
            catch (IOException e)
            {
                System.out.println("Intruder's Thunder: IO Exception");
            }

            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
            Image image = gc.createCompatibleImage(source.getWidth(),source.getHeight(),Transparency.BITMASK);

            image.getGraphics().drawImage(source,0,0,null);

            Sprite newSprite = new Sprite(image, id);
            //sprites.add(newSprite);
            sprites[id] = newSprite;

            return newSprite;

        }

        return null;
    }

    private String getRef(int id)
    {
        //These are all subject to change
        if (id == 0)
        {
            return "Sprites/Tile0.gif";
        }
        else if (id == 1)
        {
            return "Sprites/PlayerBack.gif";
        }
        else if (id == 2)
        {
            return "Sprites/PlayerFront.gif";
        }
        else if (id == 3)
        {
            return "Sprites/PlayerLeft.gif";
        }
        else if (id == 4)
        {
            return "Sprites/PlayerRight.gif";
        }
        else if (id == 5)
        {
            return "Sprites/PlayerBackRun.gif";
        }
        else if (id == 6)
        {
            return "Sprites/PlayerFrontRun.gif";
        }
        else if (id == 7)
        {
            return "Sprites/PlayerLeftRun.gif";
        }
        else if (id == 8)
        {
            return "Sprites/PlayerRightRun.gif";
        }
        else if (id == 9)
        {
            return "Sprites/PlayerBackAttack.gif";
        }
        else if (id == 10)
        {
            return "Sprites/PlayerFrontKnife.gif";
        }
        else if (id == 11)
        {
            return "Sprites/PlayerLeftKnife.gif";
        }
        else if (id == 12)
        {
            return "Sprites/PlayerRightKnife.gif";
        }
        else if (id == 13)
        {
            return "Sprites/PlayerFrontPistol.gif";
        }
        else if (id == 14)
        {
            return "Sprites/PlayerLeftPistol.gif";
        }
        else if (id == 15)
        {
            return "Sprites/PlayerRightPistol.gif";
        }
        else if (id == 16)
        {
            return "Sprites/PlayerFrontAR.gif";
        }
        else if (id == 17)
        {
            return "Sprites/PlayerLeftAR.gif";
        }
        else if (id == 18)
        {
            return "Sprites/PlayerRightAR.gif";
        }
        else if (id == 19)
        {
            return "Sprites/LightGuardBack.gif";
        }
        else if (id == 20)
        {
            return "Sprites/LightGuardFront.gif";
        }
        else if (id == 21)
        {
            return "Sprites/LightGuardLeft.gif";
        }
        else if (id == 22)
        {
            return "Sprites/LightGuardRight.gif";
        }
        else if (id == 23)
        {
            return "Sprites/LightGuardBackRun.gif";
        }
        else if (id == 24)
        {
            return "Sprites/LightGuardFrontRun.gif";
        }
        else if (id == 25)
        {
            return "Sprites/LightGuardLeftRun.gif";
        }
        else if (id == 26)
        {
            return "Sprites/LightGuardRightRun.gif";
        }
        else if (id == 27)
        {
            return "Sprites/MediumGuardBack.gif";
        }
        else if (id == 28)
        {
            return "Sprites/MediumGuardFront.gif";
        }
        else if (id == 29)
        {
            return "Sprites/MediumGuardLeft.gif";
        }
        else if (id == 30)
        {
            return "Sprites/MediumGuardRight.gif";
        }
        else if (id == 31)
        {
            return "Sprites/MediumGuardBackRun.gif";
        }
        else if (id == 32)
        {
            return "Sprites/MediumGuardFrontRun.gif";
        }
        else if (id == 33)
        {
            return "Sprites/MediumGuardLeftRun.gif";
        }
        else if (id == 34)
        {
            return "Sprites/MediumGuardRightRun.gif";
        }
        else if (id == 35)
        {
            return "Sprites/HeavyGuardBack.gif";
        }
        else if (id == 36)
        {
            return "Sprites/HeavyGuardFront.gif";
        }
        else if (id == 37)
        {
            return "Sprites/HeavyGuardLeft.gif";
        }
        else if (id == 38)
        {
            return "Sprites/HeavyGuardRight.gif";
        }
        else if (id == 39)
        {
            return "Sprites/HeavyGuardBackRun.gif";
        }
        else if (id == 40)
        {
            return "Sprites/HeavyGuardFrontRun.gif";
        }
        else if (id == 41)
        {
            return "Sprites/HeavyGuardLeftRun.gif";
        }
        else if (id == 42)
        {
            return "Sprites/HeavyGuardRightRun.gif";
        }
        else if (id == 43)
        {
            return "Sprites/water1.jpg"; //was Water.gif
        }
        else if (id == 44)
        {
            return "Sprites/sand.jpg"; //was Sand.gif
        }
        else if (id == 45)
        {
            return "Sprites/tallgrass.png"; //was TallGrass.gif
        }
        else if (id == 46)
        {
            return "Sprites/gate.gif";
        }
        else if (id == 47)
        {
            return "Sprites/tree.gif";
        }
        else if (id == 48)
        {
            return "Sprites/bricks.gif";
        }
        else if (id == 49)
        {
            return "Sprites/HorizontalPipes.gif";
        }
        else if (id == 50)
        {
            return "Sprites/VerticalPipes.gif";
        }
        else if (id == 51)
        {
            return "Sprites/Concrete2.gif";
        }
        else if (id == 52)
        {
            return "Sprites/RockWall.gif";
        }
        else if (id == 53)
        {
            return "Sprites/SimpleConcrete.gif";
        }
        else if (id == 54)
        {
            return "Sprites/DryWall.gif";
        }
        else if (id == 55)
        {
            return "Sprites/IndoorFloorTile.gif";
        }
        else if (id == 56)
        {
            return "Sprites/WoodFloor.gif";
        }
        else if (id == 57)
        {
            return "Sprites/Door.gif";
        }
        else if (id == 58)
        {
            return "Sprites/SecurityDoor.gif";
        }
        else if (id == 59)
        {
            return "Sprites/SecurityDoorOpen.gif";
        }
        else if (id == 60)
        {
            return "Sprites/VerticalRoadCenter.gif";
        }
        else if (id == 61)
        {
            return "Sprites/HorizontalRoadCenter.gif";
        }
        else if (id == 62)
        {
            return "Sprites/HorizontalRoadSideLeft.gif";
        }
        else if (id == 63)
        {
            return "Sprites/HorizontalRoadSideRight.gif";
        }
        else if (id == 64)
        {
            return "Sprites/VerticalRoadSideLeft.gif";
        }
        else if (id == 65)
        {
            return "Sprites/VerticalRoadSideRight.gif";
        }
        else if (id == 66)
        {
            return "Sprites/RoadSideLowerLeftCorner.gif";
        }
        else if (id == 67)
        {
            return "Sprites/RoadSideLowerRightCorner.gif";
        }
        else if (id == 68)
        {
            return "Sprites/RoadSideUpperLeftCorner.gif";
        }
        else if (id == 69)
        {
            return "Sprites/RoadSideUpperRightCorner.gif";
        }
        else if (id == 70)
        {
            return "Sprites/HealthKit.gif";
        }
        else if (id == 71)
        {
            return "Sprites/Pistol.png";
        }
        else if (id == 72)
        {
            return "Sprites/Silencer.gif";
        }
        else if (id == 73)
        {
            return "Sprites/AR.png";
        }
        else if (id == 74)
        {
            return "Sprites/Ammo.gif";
        }
        else if (id == 75)
        {
            return "Sprites/Cardkey1.gif";
        }
        else if (id == 76)
        {
            return "Sprites/Cardkey2.gif";
        }
        else if (id == 77)
        {
            return "Sprites/Cardkey3.gif";
        }
        else if (id == 78)
        {
            return "Sprites/grass.jpg"; //Was Grass.gif
        }
        else if (id == 79)
        {
            return "Sprites/Snow.gif";
        }
        else if (id == 80)
        {
            return "Sprites/IndoorFloorTile2.gif";
        }
        else if (id == 81)
        {
            return "Sprites/IndoorFloorTile3.gif";
        }
        else if (id == 82)
        {
            return "Sprites/IndoorFloorTile4.gif";
        }
        else if (id == 83)
        {
            return "Sprites/water2.jpg"; //Was NightWater.gif
        }
        else if (id == 84)
        {
            return "Sprites/grass2.jpg"; //Was NightSand.gif
        }
        else if (id == 85)
        {
            return "Sprites/LadderUp.gif";
        }
        else if (id == 86)
        {
            return "Sprites/LadderDown.gif";
        }
        else if (id == 87)
        {
            return "Sprites/WaterTrail.gif";
        }
        else if (id == 88)
        {
            return "Sprites/PlayerBackCrawl.gif";
        }
        else if (id == 89)
        {
            return "Sprites/PlayerFrontCrawl.gif";
        }
        else if (id == 90)
        {
            return "Sprites/PlayerLeftCrawl.gif";
        }
        else if (id == 91)
        {
            return "Sprites/PlayerRightCrawl.gif";
        }
        else if (id == 92)
        {
            return "Sprites/PlayerBackCrawling.gif";
        }
        else if (id == 93)
        {
            return "Sprites/PlayerFrontCrawling.gif";
        }
        else if (id == 94)
        {
            return "Sprites/PlayerLeftCrawling.gif";
        }
        else if (id == 95)
        {
            return "Sprites/PlayerRightCrawling.gif";
        }
        else if (id == 96)
        {
            return "Sprites/BoosterPack.gif";
        }
        else if (id == 97)
        {
            return "Sprites/desert.jpg"; //was DesertGround.gif
        }
        else if (id == 98)
        {
            return "Sprites/CobbleStone.gif";
        }
        else if (id == 99)
        {
            return "Sprites/pinetreetop.png";
        }
        else if (id == 100)
        {
            return "Sprites/pinetreebottom.png";
        }
        else if (id == 101)
        {
            return "Sprites/PalmTreeTop.png";
        }
        else if (id == 102)
        {
            return "Sprites/PalmTreeBottom.png";
        }
        else if (id == 103)
        {
            return "Sprites/streetlight_left_top.gif";
        }
        else if (id == 104)
        {
            return "Sprites/streetlight_left_bottom.gif";
        }
        else if (id == 105)
        {
            return "Sprites/streetlight_right_top.gif";
        }
        else if (id == 106)
        {
            return "Sprites/streetlight_right_bottom.gif";
        }
        else if (id == 107)
        {
            return "Sprites/Crate.gif";
        }
        else if (id == 108)
        {
            return "Sprites/Bloodstain.gif";
        }
        else if (id == 109)
        {
            return "Sprites/DarkSolid.gif";
        }
        else if (id == 110)
        {
            return "Sprites/SubTopLeft.gif";
        }
        else if (id == 111)
        {
            return "Sprites/SubTopRight.gif";
        }
        else if (id == 112)
        {
            return "Sprites/computer.png";
        }
        else if (id == 113)
        {
            return "Sprites/NoseCone.gif";
        }
        else if (id == 114)
        {
            return "Sprites/MissileSegment.gif";
        }
        else if (id == 115)
        {
            return "Sprites/LeftFin.gif";
        }
        else if (id == 116)
        {
            return "Sprites/MiddleFin.gif";
        }
        else if (id == 117)
        {
            return "Sprites/RightFin.gif";
        }
        else if (id == 118)
        {
            return "Sprites/FancyFloor.gif";
        }
        else if (id == 119)
        {
            return "Sprites/Boss1Back.gif";
        }
        else if (id == 120)
        {
            return "Sprites/Boss1Front.gif";
        }
        else if (id == 121)
        {
            return "Sprites/Boss1Left.gif";
        }
        else if (id == 122)
        {
            return "Sprites/Boss1Right.gif";
        }
        else if (id == 123)
        {
            return "Sprites/Boss1BackRun.gif";
        }
        else if (id == 124)
        {
            return "Sprites/Boss1FrontRun.gif";
        }
        else if (id == 125)
        {
            return "Sprites/Boss1LeftRun.gif";
        }
        else if (id == 126)
        {
            return "Sprites/Boss1RightRun.gif";
        }
        else if (id == 127)
        {
            return "Sprites/Boss2Back.gif";
        }
        else if (id == 128)
        {
            return "Sprites/Boss2Front.gif";
        }
        else if (id == 129)
        {
            return "Sprites/Boss2Left.gif";
        }
        else if (id == 130)
        {
            return "Sprites/Boss2Right.gif";
        }
        else if (id == 131)
        {
            return "Sprites/Boss2BackRun.gif";
        }
        else if (id == 132)
        {
            return "Sprites/Boss2FrontRun.gif";
        }
        else if (id == 133)
        {
            return "Sprites/Boss2LeftRun.gif";
        }
        else if (id == 134)
        {
            return "Sprites/Boss2RightRun.gif";
        }
        else if (id == 135)
        {
            return "Sprites/Boss3Back.gif";
        }
        else if (id == 136)
        {
            return "Sprites/Boss3Front.gif";
        }
        else if (id == 137)
        {
            return "Sprites/Boss3Left.gif";
        }
        else if (id == 138)
        {
            return "Sprites/Boss3Right.gif";
        }
        else if (id == 139)
        {
            return "Sprites/Boss3BackRun.gif";
        }
        else if (id == 140)
        {
            return "Sprites/Boss3FrontRun.gif";
        }
        else if (id == 141)
        {
            return "Sprites/Boss3LeftRun.gif";
        }
        else if (id == 142)
        {
            return "Sprites/Boss3RightRun.gif";
        }
        else if (id == 143)
        {
            return "Sprites/IndoorFloorTile5.gif";
        }
        else if (id == 144)
        {
            return "Sprites/IndoorFloorTile6.gif";
        }
        else if (id == 145)
        {
            return "Sprites/bed1.png";
        }
        else if (id == 146)
        {
            return "Sprites/bed2.png";
        }
        else if (id == 147)
        {
            return "Sprites/chair.png";
        }
        else if (id == 148)
        {
            return "Sprites/desk.png";
        }
        else if (id == 149)
        {
            return "Sprites/desk2.png";
        }
        else if (id == 150)
        {
            return "Sprites/computer_slacker.png";
        }
        else if (id == 151)
        {
            return "Sprites/computer_slacker2.png";
        }
        else if (id == 152)
        {
            return "Sprites/conferencedeskbottom.png";
        }
        else if (id == 153)
        {
            return "Sprites/conferencedesktop.png";
        }
        else if (id == 154)
        {
            return "Sprites/binaryfloor.gif";
        }
        else if (id == 155)
        {
            return "Sprites/binarywall.gif";
        }
        else if (id == 156)
        {
            return "Sprites/painting.gif";
        }
        else if (id == 157)
        {
            return "Sprites/fancywall.gif";
        }
        else if (id == 158)
        {
            return "Sprites/locker.gif";
        }
        else if (id == 159)
        {
            return "Sprites/computer2.gif";
        }
        else if (id == 160)
        {
            return "Sprites/crate2.gif";
        }
        else if (id == 161)
        {
            return "Sprites/tombstone.gif";
        }
        else if (id == 162)
        {
            return "Sprites/Scientist1Back.gif";
        }
        else if (id == 163)
        {
            return "Sprites/Scientist1Front.gif";
        }
        else if (id == 164)
        {
            return "Sprites/Scientist1Left.gif";
        }
        else if (id == 165)
        {
            return "Sprites/Scientist1Right.gif";
        }
        else if (id == 166)
        {
            return "Sprites/Scientist1BackRun.gif";
        }
        else if (id == 167)
        {
            return "Sprites/Scientist1FrontRun.gif";
        }
        else if (id == 168)
        {
            return "Sprites/Scientist1LeftRun.gif";
        }
        else if (id == 169)
        {
            return "Sprites/Scientist1RightRun.gif";
        }
        else if (id == 170)
        {
            return "Sprites/Scientist2Back.gif";
        }
        else if (id == 171)
        {
            return "Sprites/Scientist2Front.gif";
        }
        else if (id == 172)
        {
            return "Sprites/Scientist2Left.gif";
        }
        else if (id == 173)
        {
            return "Sprites/Scientist2Right.gif";
        }
        else if (id == 174)
        {
            return "Sprites/Scientist2BackRun.gif";
        }
        else if (id == 175)
        {
            return "Sprites/Scientist2FrontRun.gif";
        }
        else if (id == 176)
        {
            return "Sprites/Scientist2LeftRun.gif";
        }
        else if (id == 177)
        {
            return "Sprites/Scientist2RightRun.gif";
        }
        else if (id == 178)
        {
            return "Sprites/StoveTop.gif";
        }
        else if (id == 179)
        {
            return "Sprites/chemlab.png";
        }
        else if (id == 180)
        {
            return "Sprites/GlassWall.gif";
        }
        else if (id == 181)
        {
            return "Sprites/Bullet.gif";
        }
        else if (id == 182)
        {
            return "Sprites/RadarFree.gif";
        }
        else if (id == 183)
        {
            return "Sprites/RadarObstacle.gif";
        }
        else if (id == 184)
        {
            return "Sprites/RadarPlayer.gif";
        }
        else if (id == 185)
        {
            return "Sprites/RadarNPC.gif";
        }
        else if (id == 186)
        {
            return "Sprites/cactus.png";
        }
        else if (id == 187)
        {
            return "Sprites/garagedoor.gif";
        }
        else if (id == 188)
        {
            return "Sprites/PlayerFrontSMG.gif";
        }
        else if (id == 189)
        {
            return "Sprites/PlayerLeftSMG.gif";
        }
        else if (id == 190)
        {
            return "Sprites/PlayerRightSMG.gif";
        }
        else if (id == 191)
        {
            return "Sprites/Grenade.gif";
        }
        else if (id == 192)
        {
            return "Sprites/female_ally.gif";
        }
        else if (id == 193)
        {
            return "Sprites/Explosion2.gif";
        }
        else if (id == 194)
        {
            return "Sprites/PlayerFrontShotgun.gif";
        }
        else if (id == 195)
        {
            return "Sprites/PlayerLeftShotgun.gif";
        }
        else if (id == 196)
        {
            return "Sprites/PlayerRightShotgun.gif";
        }
        else if (id == 197)
        {
            return "Sprites/CameraUp.gif";
        }
        else if (id == 198)
        {
            return "Sprites/CameraDown.gif";
        }
        else if (id == 199)
        {
            return "Sprites/CameraLeft.gif";
        }
        else if (id == 200)
        {
            return "Sprites/CameraRight.gif";
        }
        else if (id == 201)
        {
            return "Sprites/Cardkey4.gif";
        }
        else if (id == 202)
        {
            return "Sprites/Cardkey5.gif";
        }
        else if (id == 203)
        {
            return "Sprites/NVG.gif";
        }
        else if (id == 204)
        {
            return "Sprites/Gasmask.gif";
        }
        else if (id == 205)
        {
            return "Sprites/SMG.png";
        }
        else if (id == 206)
        {
            return "Sprites/Shotgun.gif";
        }
        else if (id == 207)
        {
            return "Sprites/BodyArmor.gif";
        }
        else if (id == 208)
        {
            return "Sprites/bookcase.png";
        }
        else if (id == 209)
        {
            return "Sprites/GrassWall.gif";
        }
        else if (id == 210)
        {
            return "Sprites/tank00.png";
        }
        else if (id == 211)
        {
            return "Sprites/tank10.png";
        }
        else if (id == 212)
        {
            return "Sprites/tank20.png";
        }
        else if (id == 213)
        {
            return "Sprites/tank01.png";
        }
        else if (id == 214)
        {
            return "Sprites/tank11.png";
        }
        else if (id == 215)
        {
            return "Sprites/tank21.png";
        }
        else if (id == 216)
        {
            return "Sprites/tank02.png";
        }
        else if (id == 217)
        {
            return "Sprites/tank12.png";
        }
        else if (id == 218)
        {
            return "Sprites/tank22.png";
        }
        else if (id == 219)
        {
            return "Sprites/tankbarrel.png";
        }
        else if (id == 220)
        {
            return "Sprites/HorizontalStairs.gif";
        }
        else if (id == 221)
        {
            return "Sprites/VerticalStairs.gif";
        }
        else if (id == 222)
        {
            return "Sprites/Objective1.gif";
        }
        else if (id == 223)
        {
            return "Sprites/Objective2.gif";
        }
        else if (id == 224)
        {
            return "Sprites/Objective3.gif";
        }
        else if (id == 225)
        {
            return "Sprites/Objective4.gif";
        }
        else if (id == 226)
        {
           return "Sprites/Boss4Back.gif";
        }
        else if (id == 227)
        {
           return "Sprites/Boss4Front.gif";
        }
        else if (id == 228)
        {
           return "Sprites/Boss4Left.gif";
        }
        else if (id == 229)
        {
           return "Sprites/Boss4Right.gif";
        }
        else if (id == 230)
        {
           return "Sprites/Boss4BackRun.gif";
        }
        else if (id == 231)
        {
           return "Sprites/Boss4FrontRun.gif";
        }
        else if (id == 232)
        {
           return "Sprites/Boss4LeftRun.gif";
        }
        else if (id == 233)
        {
           return "Sprites/Boss4RightRun.gif";
        }
        else if (id == 234)
        {
           return "Sprites/Boss5Back.gif";
        }
        else if (id == 235)
        {
           return "Sprites/Boss5Front.gif";
        }
        else if (id == 236)
        {
           return "Sprites/Boss5Left.gif";
        }
        else if (id == 237)
        {
           return "Sprites/Boss5Right.gif";
        }
        else if (id == 238)
        {
           return "Sprites/Boss5BackRun.gif";
        }
        else if (id == 239)
        {
           return "Sprites/Boss5FrontRun.gif";
        }
        else if (id == 240)
        {
           return "Sprites/Boss5LeftRun.gif";
        }
        else if (id == 241)
        {
           return "Sprites/Boss5RightRun.gif";
        }
        else if (id == 242)
        {
           return "Sprites/Boss6Back.gif";
        }
        else if (id == 243)
        {
           return "Sprites/Boss6Front.gif";
        }
        else if (id == 244)
        {
           return "Sprites/Boss6Left.gif";
        }
        else if (id == 245)
        {
           return "Sprites/Boss6Right.gif";
        }
        else if (id == 246)
        {
           return "Sprites/Boss6BackRun.gif";
        }
        else if (id == 247)
        {
           return "Sprites/Boss6FrontRun.gif";
        }
        else if (id == 248)
        {
           return "Sprites/Boss6LeftRun.gif";
        }
        else if (id == 249)
        {
           return "Sprites/Boss6RightRun.gif";
        }
        else if (id == 250)
        {
            return "Sprites/Wristwatch.gif";
        }
        else if (id == 251)
        {
            return "Sprites/LocationObjective.gif";
        }
        else if (id == 252){return "Sprites/templewall.jpg";}
        else if (id == 253){return "Sprites/alienwall.jpg";}
        else if (id == 254){return "Sprites/water3.jpg";}
        else if (id == 255){return "Sprites/crimescene.gif";}
        else if (id == 256){return "Sprites/woodfloor2.jpg";}
        else if (id == 257){return "Sprites/templefloor.jpg";}
        else if (id == 258){return "Sprites/surrogate1.gif";}
        else if (id == 259){return "Sprites/surrogate2.gif";}
        else if (id == 260){return "Sprites/surrogate3.gif";}
        else if (id == 261){return "Sprites/female_ally_back.gif";}
        else if (id == 262){return "Sprites/female_ally_front.gif";}
        else if (id == 263){return "Sprites/female_ally_left.gif";}
        else if (id == 264){return "Sprites/female_ally_right.gif";}
        else if (id == 265){return "Sprites/female_ally_back_run.gif";}
        else if (id == 266){return "Sprites/female_ally_front_run.gif";}
        else if (id == 267){return "Sprites/female_ally_left_run.gif";}
        else if (id == 268){return "Sprites/female_ally_right_run.gif";}
        else if (id == 269){return "Sprites/female_ally_prisoner_back.gif";}
        else if (id == 270){return "Sprites/female_ally_prisoner_front.gif";}
        else if (id == 271){return "Sprites/female_ally_prisoner_left.gif";}
        else if (id == 272){return "Sprites/female_ally_prisoner_right.gif";}
        else if (id == 273){return "Sprites/female_ally_prisoner_back_run.gif";}
        else if (id == 274){return "Sprites/female_ally_prisoner_front_run.gif";}
        else if (id == 275){return "Sprites/female_ally_prisoner_left_run.gif";}
        else if (id == 276){return "Sprites/female_ally_prisoner_right_run.gif";}
        else if (id == 277){return "Sprites/WormUp.gif";}
        else if (id == 278){return "Sprites/WormDown.gif";}
        else if (id == 279){return "Sprites/WormLeft.gif";}
        else if (id == 280){return "Sprites/WormRight.gif";}
        else if (id == 281){return "Sprites/WormUp2.gif";}
        else if (id == 282){return "Sprites/WormDown2.gif";}
        else if (id == 283){return "Sprites/WormLeft2.gif";}
        else if (id == 284){return "Sprites/WormRight2.gif";}
        else if (id == 285){return "Sprites/LarveUp.gif";}
        else if (id == 286){return "Sprites/LarveDown.gif";}
        else if (id == 287){return "Sprites/LarveLeft.gif";}
        else if (id == 288){return "Sprites/LarveRight.gif";}
        else if (id == 289){return "Sprites/LarveUp2.gif";}
        else if (id == 290){return "Sprites/LarveDown2.gif";}
        else if (id == 291){return "Sprites/LarveLeft2.gif";}
        else if (id == 292){return "Sprites/LarveRight2.gif";}
        else if (id == 293){return "Sprites/mine.gif";}
        else if (id == 294){return "Sprites/laser_horizontal.gif";}
        else if (id == 295){return "Sprites/laser_vertical.gif";}
        else if (id == 296){return "Sprites/MineDetector.gif";}
        else if (id == 297){return "Sprites/white.gif";}
        else if (id == 298){return "Sprites/borderbox.gif";}

        else if (id == 299){return "Sprites/metalboxtop1.gif";}
        else if (id == 300){return "Sprites/metalboxbottom1.gif";}
        else if (id == 301){return "Sprites/hshadow1.gif";}
        else if (id == 302){return "Sprites/vshadow1.gif";}
        else if (id == 303){return "Sprites/cshadow1.gif";}

        else if (id == 308){return "Sprites/RoadCornerLeft.gif";}
        else if (id == 309){return "Sprites/RoadCornerRight.gif";}

        else if (id == 310){return "Sprites/Woman1Back.gif";}
        else if (id == 311){return "Sprites/Woman1Front.gif";}
        else if (id == 312){return "Sprites/Woman1Left.gif";}
        else if (id == 313){return "Sprites/Woman1Right.gif";}
        else if (id == 314){return "Sprites/Woman1BackRun.gif";}
        else if (id == 315){return "Sprites/Woman1FrontRun.gif";}
        else if (id == 316){return "Sprites/Woman1LeftRun.gif";}
        else if (id == 317){return "Sprites/Woman1RightRun.gif";}

        else if (id == 318){return "Sprites/Woman2Back.gif";}
        else if (id == 319){return "Sprites/Woman2Front.gif";}
        else if (id == 320){return "Sprites/Woman2Left.gif";}
        else if (id == 321){return "Sprites/Woman2Right.gif";}
        else if (id == 322){return "Sprites/Woman2BackRun.gif";}
        else if (id == 323){return "Sprites/Woman2FrontRun.gif";}
        else if (id == 324){return "Sprites/Woman2LeftRun.gif";}
        else if (id == 325){return "Sprites/Woman2RightRun.gif";}

        else if (id == 326){return "Sprites/Woman3Back.gif";}
        else if (id == 327){return "Sprites/Woman3Front.gif";}
        else if (id == 328){return "Sprites/Woman3Left.gif";}
        else if (id == 329){return "Sprites/Woman3Right.gif";}
        else if (id == 330){return "Sprites/Woman3BackRun.gif";}
        else if (id == 331){return "Sprites/Woman3FrontRun.gif";}
        else if (id == 332){return "Sprites/Woman3LeftRun.gif";}
        else if (id == 333){return "Sprites/Woman3RightRun.gif";}

        else if (id == 334){return "Sprites/chief_back.gif";}
        else if (id == 335){return "Sprites/chief_front.gif";}
        else if (id == 336){return "Sprites/chief_left.gif";}
        else if (id == 337){return "Sprites/chief_right.gif";}
        else if (id == 338){return "Sprites/chief_back_run.gif";}
        else if (id == 339){return "Sprites/chief_front_run.gif";}
        else if (id == 340){return "Sprites/chief_left_run.gif";}
        else if (id == 341){return "Sprites/chief_right_run.gif";}

        else if (id == 342){return "Sprites/ig_back.gif";}
        else if (id == 343){return "Sprites/ig_front.gif";}
        else if (id == 344){return "Sprites/ig_left.gif";}
        else if (id == 345){return "Sprites/ig_right.gif";}
        else if (id == 346){return "Sprites/ig_back_run.gif";}
        else if (id == 347){return "Sprites/ig_front_run.gif";}
        else if (id == 348){return "Sprites/ig_left_run.gif";}
        else if (id == 349){return "Sprites/ig_right_run.gif";}

        else if (id == 350){return "Sprites/SpecialGuardBack.gif";}
        else if (id == 351){return "Sprites/SpecialGuardFront.gif";}
        else if (id == 352){return "Sprites/SpecialGuardLeft.gif";}
        else if (id == 353){return "Sprites/SpecialGuardRight.gif";}
        else if (id == 354){return "Sprites/SpecialGuardBackRun.gif";}
        else if (id == 355){return "Sprites/SpecialGuardFrontRun.gif";}
        else if (id == 356){return "Sprites/SpecialGuardLeftRun.gif";}
        else if (id == 357){return "Sprites/SpecialGuardRightRun.gif";}

        else if (id == 358){return "Sprites/truck00.png";}
        else if (id == 359){return "Sprites/truck10.png";}
        else if (id == 360){return "Sprites/truck20.png";}
        else if (id == 361){return "Sprites/truck01.png";}
        else if (id == 362){return "Sprites/truck11.png";}
        else if (id == 363){return "Sprites/truck21.png";}
        else if (id == 364){return "Sprites/truck02.png";}
        else if (id == 365){return "Sprites/truck12.png";}
        else if (id == 366){return "Sprites/truck22.png";}

        else if (id == 367){return "Sprites/FireExtinguisher.gif";}
        else if (id == 368){return "Sprites/c4.gif";}

        else if (id == 369){return "Sprites/RoadCornerLeft2.gif";}
        else if (id == 370){return "Sprites/RoadCornerRight2.gif";}

        else if (id == 371){return "Sprites/painting2.gif";}
        else if (id == 372){return "Sprites/painting3.gif";}
        else if (id == 373){return "Sprites/painting4.gif";}
        else if (id == 374){return "Sprites/painting5.gif";}

        else if (id == 375){return "Sprites/snowparticle.gif";}
        else if (id == 376){return "Sprites/rainparticle.gif";}

        else if (id == 377){return "Sprites/airventclosed.gif";}
        else if (id == 378){return "Sprites/airventopen.gif";}

        else if (id == 379){return "Sprites/wateredgetop.png";}
        else if (id == 380){return "Sprites/wateredgebottom.png";}
        else if (id == 381){return "Sprites/wateredgeleft.png";}
        else if (id == 382){return "Sprites/wateredgeright.png";}
        else if (id == 383){return "Sprites/wateredgetopleft.png";}
        else if (id == 384){return "Sprites/wateredgetopright.png";}
        else if (id == 385){return "Sprites/wateredgebottomleft.png";}
        else if (id == 386){return "Sprites/wateredgebottomright.png";}
        else if (id == 387){return "Sprites/wateredgetopleftjunction.png";}
        else if (id == 388){return "Sprites/wateredgetoprightjunction.png";}
        else if (id == 389){return "Sprites/wateredgebottomleftjunction.png";}
        else if (id == 390){return "Sprites/wateredgebottomrightjunction.png";}

        else if (id == 391){return "Sprites/bathroom.png";}

        return "Sprites/Tile0.gif";

    }
}
