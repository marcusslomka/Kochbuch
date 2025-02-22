CREATE TABLE IF NOT EXISTS recipes (
id string PRIMARY KEY,
title string,
description string
);

CREATE TABLE IF NOT EXISTS ingredients (
id string PRIMARY KEY,
name string,
description string
);

