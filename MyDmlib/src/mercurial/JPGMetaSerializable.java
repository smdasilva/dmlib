package mercurial;


import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abndoye
 */
public class JPGMetaSerializable implements Serializable {
    
    public String Aperture;
    public String LensModel;       
    public String  ColorSpace;      
    public String CustomRendered;
    public String DateTimeDigitized;       
    public String  DateTimeOriginal;      
    public String  ExifVersion;      
    public String ExposureBiasValue;
    public String ExposureMode;       
    public String ExposureTime;       
    public String ExposureProgram;       
    public String Flash;
    public String FlashPixVersion;
    public String FocalLength;
    public String FocalPlanResolutionUnit;       
    public String  FocalPlanXResolution;     
    public String FocalPlanYResolution;
    public String IsoSpeddRatings;      
    public String MeteringMode;       
    public String SceneCaptureType;
    public String ShutterSpeedValue;
    public String WhiteBalance;   
            
    public JPGMetaSerializable(String Aperture,
            String LensModel, 
            String  ColorSpace,      
            String CustomRendered, 
            String DateTimeDigitized, 
            String  DateTimeOriginal, 
            String  ExifVersion,      
            String ExposureBiasValue, 
            String ExposureMode, 
            String ExposureTime, 
            String ExposureProgram,       
            String Flash, 
            String FlashPixVersion, 
            String FocalLength, 
            String FocalPlanResolutionUnit, 
            String  FocalPlanXResolution,     
            String FocalPlanYResolution, 
            String IsoSpeddRatings, 
            String MeteringMode, 
            String SceneCaptureType,
            String ShutterSpeedValue,  
            String WhiteBalance)
    {
        this.Aperture = Aperture;
        this.LensModel = LensModel;
        this.ColorSpace = ColorSpace;
        this.CustomRendered = CustomRendered;
        this.DateTimeDigitized = DateTimeDigitized;
        this.DateTimeOriginal = DateTimeOriginal;
        this.ExifVersion = ExifVersion;
        this.ExposureBiasValue = ExposureBiasValue;
        this.ExposureMode = ExposureMode;
        this.ExposureTime = ExposureTime;
        this.ExposureProgram = ExposureProgram;
        this.Flash = Flash;
        this.FlashPixVersion = FlashPixVersion;
        this.FocalLength = FocalLength;
        this.FocalPlanResolutionUnit = FocalPlanResolutionUnit;
        this.FocalPlanXResolution = FocalPlanXResolution;
        this.FocalPlanYResolution = FocalPlanYResolution;
        this.IsoSpeddRatings = IsoSpeddRatings;
        this.MeteringMode = MeteringMode;
        this.SceneCaptureType = SceneCaptureType;
        this.ShutterSpeedValue = ShutterSpeedValue;
        this.WhiteBalance = WhiteBalance;      
    }

    /**
     * @return the aperture
     */
    public String getAperture() {
        return Aperture;
    }

    

    

    /**
     * @return the LensModel
     */
    public String getLensModel() {
        return LensModel;
    }

    /**
     * @return the ColorSpace
     */
    public String getColorSpace() {
        return ColorSpace;
    }

    /**
     * @return the CustomRendered
     */
    public String getCustomRendered() {
        return CustomRendered;
    }

    /**
     * @return the DateTimeDigitized
     */
    public String getDateTimeDigitized() {
        return DateTimeDigitized;
    }

    /**
     * @return the DateTimeOriginal
     */
    public String getDateTimeOriginal() {
        return DateTimeOriginal;
    }

    /**
     * @return the ExifVersion
     */
    public String getExifVersion() {
        return ExifVersion;
    }

    /**
     * @return the ExposureBiasValue
     */
    public String getExposureBiasValue() {
        return ExposureBiasValue;
    }

    /**
     * @return the ExposureMode
     */
    public String getExposureMode() {
        return ExposureMode;
    }

    /**
     * @return the ExposureTime
     */
    public String getExposureTime() {
        return ExposureTime;
    }

    /**
     * @return the ExposureProgram
     */
    public String getExposureProgram() {
        return ExposureProgram;
    }

    /**
     * @return the Flash
     */
    public String getFlash() {
        return Flash;
    }

    /**
     * @return the FlashPixVersion
     */
    public String getFlashPixVersion() {
        return FlashPixVersion;
    }


    /**
     * @return the FocalLength
     */
    public String getFocalLength() {
        return FocalLength;
    }

    /**
     * @return the FocalPlanResolutionUnit
     */
    public String getFocalPlanResolutionUnit() {
        return FocalPlanResolutionUnit;
    }

    /**
     * @return the FocalPlanXResolution
     */
    public String getFocalPlanXResolution() {
        return FocalPlanXResolution;
    }

    /**
     * @return the FocalPlanYResolution
     */
    public String getFocalPlanYResolution() {
        return FocalPlanYResolution;
    }

    /**
     * @return the IsoSpeddRatings
     */
    public String getIsoSpeddRatings() {
        return IsoSpeddRatings;
    }

    /**
     * @return the MeteringMode
     */
    public String getMeteringMode() {
        return MeteringMode;
    }

    /**
     * @return the SceneCaptureType
     */
    public String getSceneCaptureType() {
        return SceneCaptureType;
    }

    /**
     * @return the ShutterSpeedValue
     */
    public String getShutterSpeedValue() {
        return ShutterSpeedValue;
    }


    /**
     * @return the WhiteBalance
     */
    public String getWhiteBalance() {
        return WhiteBalance;
    }

   
   
}
