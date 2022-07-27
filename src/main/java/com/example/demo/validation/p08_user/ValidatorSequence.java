package com.example.demo.validation.p08_user;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.example.demo.validation.p08_user.Groups.Group1;
import com.example.demo.validation.p08_user.Groups.Group2;
import com.example.demo.validation.p08_user.Groups.Group3;
import com.example.demo.validation.p08_user.Groups.Group4;
import com.example.demo.validation.p08_user.Groups.Group5;

@GroupSequence({Default.class, Group1.class, Group2.class, Group3.class, Group4.class, Group5.class})
public interface ValidatorSequence {}
