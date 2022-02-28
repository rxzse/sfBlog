USE [DBIBLOG01]
GO
/****** Object:  Table [dbo].[Adminstrator]    Script Date: 2/28/2022 3:51:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Adminstrator](
	[ID] [int] NOT NULL,
	[name] [nvarchar](150) NOT NULL,
	[email] [varchar](150) NOT NULL,
	[password] [varchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 2/28/2022 3:51:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[ID] [int] NOT NULL,
	[name] [nvarchar](150) NOT NULL,
	[summary] [text] NOT NULL,
	[slug] [varchar](100) NOT NULL,
	[adminID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 2/28/2022 3:51:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[ID] [int] NOT NULL,
	[content] [text] NOT NULL,
	[postID] [int] NOT NULL,
	[userID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Post]    Script Date: 2/28/2022 3:51:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post](
	[ID] [int] NOT NULL,
	[title] [nvarchar](150) NOT NULL,
	[content] [text] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
	[slug] [varchar](100) NOT NULL,
	[status] [bit] NOT NULL,
	[adminID] [int] NOT NULL,
	[userID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PostCategory]    Script Date: 2/28/2022 3:51:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PostCategory](
	[postID] [int] NOT NULL,
	[catID] [int] NOT NULL,
 CONSTRAINT [PostCategory_PK] PRIMARY KEY CLUSTERED 
(
	[postID] ASC,
	[catID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 2/28/2022 3:51:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[ID] [int] NOT NULL,
	[name] [nvarchar](150) NOT NULL,
	[email] [varchar](150) NOT NULL,
	[password] [varchar](200) NOT NULL,
	[adminID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Category]  WITH CHECK ADD  CONSTRAINT [Category_FK_Admin] FOREIGN KEY([adminID])
REFERENCES [dbo].[Adminstrator] ([ID])
GO
ALTER TABLE [dbo].[Category] CHECK CONSTRAINT [Category_FK_Admin]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [Comment_FK_Post] FOREIGN KEY([postID])
REFERENCES [dbo].[Post] ([ID])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [Comment_FK_Post]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [Comment_FK_Users] FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([ID])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [Comment_FK_Users]
GO
ALTER TABLE [dbo].[Post]  WITH CHECK ADD  CONSTRAINT [Post_FK_Admin] FOREIGN KEY([adminID])
REFERENCES [dbo].[Adminstrator] ([ID])
GO
ALTER TABLE [dbo].[Post] CHECK CONSTRAINT [Post_FK_Admin]
GO
ALTER TABLE [dbo].[Post]  WITH CHECK ADD  CONSTRAINT [Post_FK_Users] FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([ID])
GO
ALTER TABLE [dbo].[Post] CHECK CONSTRAINT [Post_FK_Users]
GO
ALTER TABLE [dbo].[PostCategory]  WITH CHECK ADD  CONSTRAINT [PCategory_FK_Category] FOREIGN KEY([catID])
REFERENCES [dbo].[Category] ([ID])
GO
ALTER TABLE [dbo].[PostCategory] CHECK CONSTRAINT [PCategory_FK_Category]
GO
ALTER TABLE [dbo].[PostCategory]  WITH CHECK ADD  CONSTRAINT [PCategory_FK_Post] FOREIGN KEY([postID])
REFERENCES [dbo].[Post] ([ID])
GO
ALTER TABLE [dbo].[PostCategory] CHECK CONSTRAINT [PCategory_FK_Post]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [User_FK_Admin] FOREIGN KEY([adminID])
REFERENCES [dbo].[Adminstrator] ([ID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [User_FK_Admin]
GO
/****** Object:  StoredProcedure [dbo].[proc_calc_comment_count]    Script Date: 2/28/2022 3:51:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[proc_calc_comment_count]
	@postID int,
	@numberOfComments INT output
AS
BEGIN
	SELECT @numberOfComments = COUNT(Comment.ID)
	FROM            Post INNER JOIN
                         Comment ON Post.ID = Comment.postID
	GROUP BY Post.ID
END
GO
