package ca.windmobile.mytestingprojects.mytestingproject;

import java.io.Serializable;

/**
 * Created by link.tmohamed on 5/3/2016.
 */
public class Node implements Serializable {
    public Node(String nodeID, String nodeName, String nodeDate) {
        this.nodeID = nodeID;
        this.nodeName = nodeName;
        this.nodeDate = nodeDate;
    }

    private String nodeID = "";
    private String nodeName = "";
    private String nodeDate = "";

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeDate() {
        return nodeDate;
    }

    public void setNodeDate(String nodeDate) {
        this.nodeDate = nodeDate;
    }


}
