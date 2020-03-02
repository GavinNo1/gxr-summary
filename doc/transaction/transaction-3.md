事务的传播机制

    REQUIRED（默认）：支持使用当前事务，如果当前事务不存在，创建一个新事务。
    SUPPORTS：支持使用当前事务，如果当前事务不存在，则不使用事务。
    MANDATORY：中文翻译为强制，支持使用当前事务，如果当前事务不存在，则抛出Exception。
    REQUIRES_NEW：创建一个新事务，如果当前事务存在，把当前事务挂起。
    NOT_SUPPORTED：无事务执行，如果当前事务存在，把当前事务挂起。
    NEVER：无事务执行，如果当前有事务则抛出Exception。
    NESTED：嵌套事务，如果当前事务存在，那么在嵌套的事务中执行。如果当前事务不存在，则表现跟REQUIRED一样
    
    项目中经常用到的是REQUIRED 对应spring中PROPAGATION_REQUIRED与REQUIRES_NEW 对应spring中PROPAGATION_REQUIRES_NEW
    