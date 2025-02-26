package com.benzhz.qcfive.repository.common;

/**
 * MongoDB RunCommand 命令操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.mongodb
 * @Project：qc-five
 * @name：RunCommandService
 * @Date：2025/2/16 22:15
 * @Filename：RunCommandService
 */
public interface RunCommandRepository {
    Object runCommand(String jsonCommand);
}
