package top.fan2wan.other.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

import java.util.List;
import java.util.Objects;

/**
 * @Author: fanT
 * @Date: 2021/5/8 14:28
 * @Description: util for docker
 */
public class DockerUtil {

    protected DockerClient dockerClient;

    /**
     * @param serverIp serverIp
     * @param port     port
     * @param useSSL   ssl
     */
    private DockerUtil(String serverIp, int port, boolean useSSL) {
        if (!useSSL) {
            DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                    .withDockerHost("tcp://" + serverIp + ":" + port)
                    .withDockerTlsVerify(false)
                    .build();
            dockerClient = DockerClientBuilder.getInstance(config).build();
        } else {
            // 需要ssl 等
            dockerClient = DockerClientBuilder.getInstance("tcp://" + serverIp + ":" + port).build();
        }
    }

    public List<SearchItem> searchImage(String imageName) {

        return dockerClient.searchImagesCmd(imageName).exec();
    }

    public CreateContainerResponse createContainer(String imageName) {
        PortBinding portBinding = PortBinding.parse("8000" + ":" + "8000");
        return dockerClient.createContainerCmd(imageName)
                .withName("test")
                .withPortBindings(portBinding)
                .withExposedPorts(ExposedPort.parse("8000" + "/tcp"))
                .exec();
    }

    public boolean startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
        return true;
    }

    /**
     * 删除容器
     *
     * @param containerId 容器id 和name 都可
     * @return boolean
     * 删除容器会检查 容器是否存在 是否运行 最薄容器正确删除
     * 当然如果容器不存在的情况 默认返回true
     */
    public boolean saveDelContainer(String containerId) {
        InspectContainerResponse inspectContainerResponse = inspectContainer(containerId);
        if (Objects.isNull(inspectContainerResponse)) {
            return true;
        }

        if (inspectContainerResponse.getState().getRunning()) {
            stopContainer(containerId);
        }

        dockerClient.removeContainerCmd(containerId).exec();
        return true;
    }


    private boolean stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
        return true;
    }

    /**
     * 检阅容器
     *
     * @param containerId 容器id 或者容器名 都可以
     * @return InspectContainerResponse 返回null 表示 容器不存在
     */
    public InspectContainerResponse inspectContainer(String containerId) {
        try {

            return dockerClient.inspectContainerCmd(containerId).exec();
        } catch (Exception e) {
            if (e instanceof NotFoundException) {
                return null;
            }
            throw new RuntimeException(e);
        }
    }

    public List<Image> listImages() {
        return dockerClient.listImagesCmd().exec();
    }

    public List<Container> listContainer() {
        return dockerClient.listContainersCmd().exec();
    }

    public Info info() {
        return dockerClient.infoCmd().exec();
    }

//============builder

    static Builder builder() {
        return new Builder();
    }


    static class Builder {
        private String serverIp;
        private int port;
        private boolean useSSL;

        public Builder withIp(String ip) {
            this.serverIp = ip;
            return this;
        }

        public Builder withPort(int port) {
            this.port = port;
            return this;
        }

        public Builder withSSL(boolean useSSLFlag) {
            this.useSSL = useSSLFlag;
            return this;
        }

        public DockerUtil build() {

            return new DockerUtil(serverIp, port, useSSL);
        }
    }

    public static void main(String[] args) {
        DockerUtil dockerUtil = DockerUtil.builder().withIp("10.0.0.241")
                .withPort(2375)
                .withSSL(false)
                .build();
//        System.out.println(dockerUtil.info());

//        dockerUtil.listContainer().forEach(System.out::println);

//        dockerUtil.listImages().forEach(System.out::println);
//        System.out.println(dockerUtil.inspectContainer("jenkins"));
//        dockerUtil.searchImage("rocketmq").forEach(System.out::println);
//        dockerUtil.createContainer("nginx:1.17.3");
        System.out.println(dockerUtil.saveDelContainer("test"));
//        System.out.println(dockerUtil.startContainer("test"));
    }
}
