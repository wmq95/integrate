package top.fan2wan.other.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InfoCmd;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

/**
 * @Author: fanT
 * @Date: 2021/5/8 14:28
 * @Description: util for docker
 */
public class DockerUtil {

    protected DockerClient dockerClient;

    public DockerUtil(String serverIp, int port, boolean useSSL) {
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


    public InfoCmd info() {
       return  dockerClient.infoCmd();
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
        DockerUtil dockerUtil = DockerUtil.builder().withIp("10.0.0.240")
                .withPort(2375)
                .withSSL(false)
                .build();
        System.out.println(dockerUtil.info());
    }
}
