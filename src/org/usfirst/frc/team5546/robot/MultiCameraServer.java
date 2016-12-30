
package org.usfirst.frc.team5546.robot;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;

/**
 * Combines multiple cameras into a single server/stream
 * @author David Sh. from 1573
 *
 */
public class MultiCameraServer {
    private int _session;
    private Image frame;
    private int _cameraCount;
    private int selectedCamera = 0;
    private boolean firstTime = true;

    /**
     * Creates a new MultiCameraServer Instance
     * @param camCount number of cameras, defines accessed cameras. eg: MultiCameraServer(2) -> ["cam0", "cam1"]
     */
    public MultiCameraServer (int camCount) {
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        _cameraCount = camCount;
    }
    
    /**
     * Send the image to the dashboard
     */
    public void run () {
        NIVision.IMAQdxGrab(_session, frame, 1);
        //NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
        /*NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);*/
        CameraServer.getInstance().setImage(frame);
    }
    
    /**
     * Change the current camera
     * @param cam new camera
     */
    public void setCamera (int cam) {
        if (cam >= _cameraCount || cam < 0) {
            System.out.println("Error: tried to set camera out of bounds.");
            return;
        }
        
        if (!firstTime) {
            NIVision.IMAQdxStopAcquisition(_session);
            NIVision.IMAQdxCloseCamera(_session);
        } else {
            firstTime = false;
        }
        selectedCamera = cam;
        _session = NIVision.IMAQdxOpenCamera("cam"+selectedCamera,
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(_session);
        NIVision.IMAQdxStartAcquisition(_session);
    }
    
    /**
     * Get currently selected camera
     * @return currently selected camera
     */
    public int getCamera () {
        return selectedCamera;
    }
    
    /**
     * Toggle between cameras, (increments, wraps back when at end)
     */
    public void toggleCamera () {
        setCamera((selectedCamera + 1) % _cameraCount);
    }
}
/*
    Example:
    camera = new MultiCameraServer(2); //RobotInit
    camera.setCamera(0); //RobotInit
    camera.run(); // Teleop periodic + Autonomous periodic + Disabled periodic
*/