package com.yc.config;

import com.yc.biz.AccountBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Quartz定时任务配置类
 * @author hl
 **/
//@Configuration
public class QuartzConfig {
    @Autowired
    private AccountBiz accountBiz;

    @Scheduled(cron="0 0/2 * * * ?")//每两分钟
    public void getTotal(){
        Double total = accountBiz.findTotalBalance();
        System.out.println("银行总余额为："+total);
    }

    @Scheduled(cron="0 0/1 * * * ?")//cron表达式   每一分钟
    public void show(){
        System.out.println("hello world");
    }
//    private static final String JOB_GROUP_NAME = "HL_JOB_GROUP_NAME";
//    private static final String TRIGGER_GROUP_NAME = "HL_TRIGGER_GROUP_NAME";
//
//    /**
//     * 定时任务1：
//     * 同步用户信息Job（任务详情）
//     */
//    @Bean
//    public JobDetail syncUserJobDetail() {
//        JobDetail jobDetail = JobBuilder.newJob(SyncUserJob.class)
//                .withIdentity("syncUserJobDetail",JOB_GROUP_NAME)
////                .usingJobData("userName", "zp") //设置参数（键值对）
////                .usingJobData("blogUrl","www.baidu.com")
////                .usingJobData("blogRemark","您好，欢迎访问")
//                .usingJobData("columnName", "balance") //设置参数（键值对）
//                .usingJobData("tableName", "accounts") //设置参数（键值对）
//                .storeDurably() //即使没有Trigger关联时，也不需要删除该JobDetail
//                .build();
//        return jobDetail;
//    }
//
//    /**
//     * 定时任务1：
//     * 同步用户信息Job（触发器）
//     */
//    @Bean
//    public Trigger syncUserJobTrigger() {
//        //每隔5秒执行一次
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
//
//        //创建触发器
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(syncUserJobDetail())//关联上述的JobDetail
//                .withIdentity("syncUserJobTrigger",TRIGGER_GROUP_NAME)//给Trigger起个名字
//                .withSchedule(cronScheduleBuilder)
//                .build();
//        return trigger;
//    }
}
