use serenity::framework::standard::{macros::command, CommandResult};
use serenity::model::prelude::*;
use serenity::prelude::*;
use tracing::{info};

#[command]
async fn help(ctx: &Context, msg: &Message) -> CommandResult {
    info!("Executed {} just now", msg.author.name);
    msg.channel_id.say(&ctx.http, "Pong!").await?;

    Ok(())

}