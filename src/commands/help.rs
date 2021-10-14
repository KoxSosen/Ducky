use serenity::framework::standard::{macros::command, CommandResult};
use serenity::model::channel::MessageType::InlineReply;
use serenity::model::prelude::*;
use serenity::prelude::*;
use serenity::utils::MessageBuilder;

use tracing::{info};

#[command]
async fn help(ctx: &Context, msg: &Message) -> CommandResult {

    let version: &str = env!("CARGO_PKG_VERSION");

    let response = MessageBuilder::new()
        .push("Ducky Rust Edition ")
        .push_line_safe(version)// Get the dynamic ver from Cargo.toml
        .push_bold_line_safe("Commands:")
        .push("\n `.help`")
        .push("\n `.cat`")
        .push("Serburs: ")
        .mention(&msg.author)
        .build();

    msg.channel_id.say(&ctx.http, &response).await?;

    Ok(())

}