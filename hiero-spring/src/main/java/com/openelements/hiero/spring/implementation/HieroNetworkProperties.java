package com.openelements.hiero.spring.implementation;

import com.openelements.hiero.base.implementation.HieroNode;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "spring.hiero.network"
)
public class HieroNetworkProperties {

    /**
     * Network name (must be mainnet, testnet or previewnet). Default is mainnet.
     * Will be ignored if nodes are provided.
     */
    private String name = "mainnet";

    /**
     * List of nodes to connect to. If provided, network name will be ignored.
     * This can be used to connect to a custom network (like Hedera Solo).
     */
    private List<HieroNode> nodes;

    /**
     * Mirror node endpoint to connect to.
     */
    private String mirrorNode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMirrorNode() {
        return mirrorNode;
    }

    public void setMirrorNode(String mirrorNode) {
        this.mirrorNode = mirrorNode;
    }

    public List<HieroNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<HieroNode> nodes) {
        this.nodes = nodes;
    }
}
